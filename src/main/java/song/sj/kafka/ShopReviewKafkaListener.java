package song.sj.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.kafka_dto.BaseShopReviewEventDto;
import song.sj.dto.kafka_dto.ShopReviewCreatedEventDto;
import song.sj.dto.kafka_dto.ShopReviewDeletedEventDto;
import song.sj.dto.kafka_dto.ShopReviewUpdatedEventDto;
import song.sj.entity.Shop;
import song.sj.repository.ShopRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional
public class ShopReviewKafkaListener {

    private final KafkaException kafkaException;
    private final ShopRepository shopRepository;
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "shop-review-topics", groupId = "shop-review-group", containerFactory = "kafkaListener")
    public void handleShopRating(String message) {

        try {
            BaseShopReviewEventDto event =
                    objectMapper.readValue(message, BaseShopReviewEventDto.class);

            Shop shop = shopRepository.findById(event.getShopId()).orElseThrow(() ->
                    new IllegalArgumentException("존재하지 않는 shop 입니다"));

            if (event instanceof ShopReviewCreatedEventDto createdEventDto) {
                shop.addReview(createdEventDto.getGrade());
            } else if (event instanceof ShopReviewDeletedEventDto deletedEventDto) {
                shop.deleteReview(deletedEventDto.getGrade());
            }/* else if (event instanceof ShopReviewUpdatedEventDto updateEventDto) {
                shop.updateReview(shop);
            }*/

        } catch (JsonProcessingException e) {
            log.error("[Kafka] Invalid JSON format for confirm-order-topics: {}", message, e);
            kafkaException.sendToDeadLetter(message);
        }
    }
}
