package song.sj.dto.order;

public class OrderBillDto {

    private String shopName;
    private int cost;
    private String itemName;
    private String repairDescription;
}

/*청구서 번호: 거래를 식별하기 위한 고유 번호
의뢰자 정보: 회원의 이름, 연락처, 이메일 주소
가게 정보: 가게 이름, 주소, 연락처
청구 날짜: 청구서가 발행된 날짜
작업 완료 예정일: 수선이 완료될 예상 날짜*/
