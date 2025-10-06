package song.sj.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import song.sj.dto.feign_dto.order.OrderInfoDto;

@Slf4j
@RequiredArgsConstructor
public class KafkaException {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String DEAD_LETTER_TOPIC = "order-topics-dlt";

    public void sendToDeadLetter(String message) {
        // 잘못된 데이터가 Consumer를 계속 죽이지 않고, 나중에 모아서 확인 가능.
        kafkaTemplate.send(DEAD_LETTER_TOPIC, message);
        log.warn("[Kafka] Sent message to Dead Letter Topic: {}", DEAD_LETTER_TOPIC);
    }

}
