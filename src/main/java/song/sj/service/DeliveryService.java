package song.sj.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.delivery.ChangeDeliveryStatusDto;
import song.sj.dto.delivery.DeliveryManagementSaveDto;
import song.sj.entity.*;
import song.sj.enums.DeliveryStatus;
import song.sj.enums.OrderStatus;
import song.sj.repository.DeliveryRepository;
import song.sj.repository.MemberRepository;
import song.sj.repository.OrderRepository;
import song.sj.repository.ShopRepository;
import song.sj.service.toEntity.ToDelivery;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;
    private final MemberService memberService;

    public void saveDelivery(Long shopId, DeliveryManagementSaveDto dto) {

        Shop shop = findShop(shopId);

        shopAuthorizationVerification(shop);

        Delivery delivery = ToDelivery.toItemEntity(dto);
        delivery.orderSetting(findOrder(dto.getOrderId()));

        checkOrderStatus(delivery);

        delivery.changeDeliveryStatus(DeliveryStatus.READY);
        delivery.memberSetting(findMember(dto.getEmail()));
        delivery.shopSetting(shop);
        deliveryRepository.save(delivery);
    }

    private Member findMember(String email) {
        return memberRepository.findByEmail(email);
    }

    private Shop findShop(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 shop 입니다."));
    }

    private Order findOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("존재하지 않는 주문 입니다."));
    }

    private static void checkOrderStatus(Delivery delivery) {
        if (!delivery.getOrder().getOrderStatus().equals(OrderStatus.COMPLETION)) {
            throw new RuntimeException("주문 상태를 완료로 변경해 주세요.");
        }
    }

    private void shopAuthorizationVerification(Shop shop) {
        if (!shop.getMember().getId().equals(memberService.getMemberFromJwt().getId())) {
            throw new AccessDeniedException("권한이 없습니다");
        }
    }

    public void changeDeliveryStatus(Long shopId, ChangeDeliveryStatusDto dto) {

        shopAuthorizationVerification(findShop(shopId));

        Delivery delivery = deliveryRepository.findById(dto.getDeliveryId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 배송 정보 입니다."));
        delivery.changeDeliveryStatus(dto.getDeliveryStatus());

        if (dto.getDeliveryStatus() == DeliveryStatus.START) {
            delivery.setStartDate(LocalDateTime.now());
        }

        if (dto.getDeliveryStatus() == DeliveryStatus.DELIVERED) {
            delivery.setDeliveredDate(LocalDateTime.now());
        }
    }
}
