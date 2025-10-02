package song.sj.dto.kafka_dto;

import lombok.Data;

@Data
public abstract class BaseShopReviewEventDto {

    private Long shopId;
    private double grade;
}
