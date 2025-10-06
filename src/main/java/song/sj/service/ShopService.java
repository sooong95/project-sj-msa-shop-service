package song.sj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.feign_dto.order.OrderInfoDto;
import song.sj.dto.shop.ShopSaveDto;
import song.sj.dto.shop_item_category.ShopItemCategorySaveDto;
import song.sj.entity.*;
import song.sj.enums.OrderStatus;
import song.sj.repository.*;
import song.sj.service.feign.OrderServiceFeignClient;
import song.sj.service.image.ImageFile;
import song.sj.service.to_entity.ToShop;
import song.sj.service.to_entity.ToShopItemCategory;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ImageFile imageFile;
    private final ShopImageRepository shopImageRepository;
    private final ShopItemCategoryRepository shopItemCategoryRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final OrderServiceFeignClient orderServiceFeignClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ShopOrderRepository shopOrderRepository;
    private final ShopOrderItemRepository shopOrderItemRepository;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    private static final String DEAD_LETTER_TOPIC = "order-topics-dlt";

    private void shopItemCategorySave(List<ItemCategory> itemCategoryNameList, Shop shop) {

        for (ItemCategory itemCategory : itemCategoryNameList) {

            ItemCategory findItemCategory = itemCategoryRepository.findByItemCategoryName(itemCategory.getItemCategoryName());

            shopItemCategoryRepository.save(ToShopItemCategory.toShopItemCategoryEntity(ShopItemCategorySaveDto.builder()
                    .itemCategory(findItemCategory)
                    .shop(shop)
                    .build()));
        }
    }

    public void save(Long userId, ShopSaveDto shopSaveDto) {

        Shop shop = ToShop.toShopEntity(shopSaveDto);

        shopRepository.save(shop);

        shop.addOwnerMemberId(userId);

        shopItemCategorySave(shopSaveDto.getItemCategoryName(), shop);
    }

    public void saveShopImages(Long shopId, List<MultipartFile> files) throws AccessDeniedException {

        if (shopRepository.findById(shopId).isEmpty()) throw new AccessDeniedException("권한이 없습니다");

        addShopImage(files, shopRepository.findById(shopId).orElseThrow());
    }

    private void addShopImage(List<MultipartFile> files, Shop shop) {
        try {
            for (MultipartFile file : files) {
                ShopImages shopImages = imageFile.serverFile(file, ShopImages.class);
                shopImageRepository.save(shopImages);
                shop.addImage(shopImageRepository.findById(shopImages.getId()).orElseThrow());
            }
        } catch (IOException e) {
            log.info("addShopImage error={}", e.getMessage());
        }
    }

    public void deleteImage(Long id) {

        ShopImages shopImage = shopImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이미지를 찾을 수 없습니다."));

        Shop shop = shopImage.getShop();

        shop.deleteImage(shopImage);

        shopImageRepository.delete(shopImage);
    }

    public void updateShop(Long id, ShopSaveDto dto) {
        Shop shop = shopRepository.findById(id).orElseThrow();
        ShopItemCategory shopItemCategory = ShopItemCategory.builder().build();
        shop.changeShopName(dto.getShopName());
        shop.changeShopDescription(dto.getShopDescription());
        dto.getItemCategoryName().stream().forEach(i -> shopItemCategory.addShopItemCategory(shop, i));
        shop.changeAddress(dto.getAddress());
    }

    @KafkaListener(topics = "confirm-order-topics", containerFactory = "kafkaListener")
    public void confirmOrderWithShop(String message) {

        try {

            OrderInfoDto orderInfoDto = objectMapper.readValue(message, OrderInfoDto.class);
            confirmOrder(orderInfoDto);
        } catch (JsonProcessingException e) {
            // 3. JSON 파싱 실패 → DLT로 메시지 전송
            log.error("[Kafka] Invalid JSON format for confirm-order-topics: {}", message, e);
            sendToDeadLetter(message);

        } catch (RuntimeException e) {
            // 4. 비즈니스 처리 실패 → 보상 트랜잭션 수행
            log.error("[Kafka] Unexpected error while processing confirm-order-topics: {}", message, e);
            handleBusinessFailure(message);
        }
    }
    private void confirmOrder(OrderInfoDto orderInfoDto) {
        log.info("[Kafka] Processing order: {}", orderInfoDto);

        // 여기에 상점에서 주문을 확인하는 실제 로직 구현
        // 예: 주문 상태 변경, 재고 감소 등
        // shopService.confirmOrder(orderInfoDto);
        // 나중에 제대로된 코드로 교체
        Shop shop = shopRepository.findById(orderInfoDto.getShopId()).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 shop 입니다."));
        ShopOrder shopOrder = ShopOrder.builder()
                .orderId(orderInfoDto.getOrderId())
                .count(orderInfoDto.getCount())
                .build();

        shopOrder.assignShopOrder(shop);
        shopOrder.changeOrderStatus(OrderStatus.ACCEPT);
        shopOrderRepository.save(shopOrder);

        orderInfoDto.getOrderItemDtoList().forEach(f -> {
            ShopOrderItem shopOrderItem = ShopOrderItem.builder()
                    .itemId(f.getItemId())
                    .itemName(f.getItemName())
                    .description(f.getDescription())
                    .itemQuantity(f.getItemQuantity())
                    .itemImageUrlList(f.getItemImageUrlList())
                    .build();
            shopOrderItem.assignShopOrderItem(shopOrder);
            shopOrderItemRepository.save(shopOrderItem);
        });

        log.info("[Kafka] Order processed successfully: {}", orderInfoDto.getOrderId());
    }

    /**
     * Dead Letter Topic으로 메시지 전송
     */
    private void sendToDeadLetter(String message) {
        // 잘못된 데이터가 Consumer를 계속 죽이지 않고, 나중에 모아서 확인 가능.
        kafkaTemplate.send(DEAD_LETTER_TOPIC, message);
        log.warn("[Kafka] Sent message to Dead Letter Topic: {}", DEAD_LETTER_TOPIC);
    }

    /**
     * 비즈니스 예외 처리 (주문 취소 등)
     */
    private void handleBusinessFailure(String message) {

        try {
            OrderInfoDto orderInfoDto = objectMapper.readValue(message, OrderInfoDto.class);
            orderServiceFeignClient.cancelOrder(orderInfoDto.getOrderId());
            log.warn("[Kafka] Canceled order due to business failure: {}", orderInfoDto.getOrderId());
        } catch (Exception e) {
            log.error("[Kafka] Failed to cancel order from message: {}", message, e);
        }
    }


}

