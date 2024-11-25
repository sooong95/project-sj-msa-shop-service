package song.sj.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderBillDto {

    private Long orderId;
    private Long shopId;
    private String shopName;
    private String shopEmail;
    private String clientEmail;
    private List<OrderItemBillDto> itemBillDtoList;
    private int totalCost;
}

/*청구서 번호: 거래를 식별하기 위한 고유 번호
의뢰자 정보: 회원의 이름, 연락처, 이메일 주소
가게 정보: 가게 이름, 주소, 연락처
청구 날짜: 청구서가 발행된 날짜
작업 완료 예정일: 수선이 완료될 예상 날짜*/
