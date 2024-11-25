package song.sj.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.delivery.deliveryManagementSaveDto;
import song.sj.entity.Delivery;
import song.sj.entity.Member;
import song.sj.enums.DeliveryStatus;
import song.sj.enums.OrderStatus;
import song.sj.repository.DeliveryRepository;
import song.sj.repository.MemberRepository;
import song.sj.repository.OrderRepository;
import song.sj.service.toEntity.ToDelivery;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public void saveDelivery(deliveryManagementSaveDto dto) {

        Member findMember = memberRepository.findByEmail(dto.getEmail());

        Delivery delivery = ToDelivery.toItemEntity(dto);

        if (!delivery.getOrder().getOrderStatus().equals(OrderStatus.COMPLETION)) {
            throw new RuntimeException("주문 상태를 완료로 변경해 주세요.");
        }

        delivery.changeDeliveryStatus(DeliveryStatus.READY);
        delivery.memberSetting(findMember);
        delivery.orderSetting(orderRepository.findById(dto.getOrderId()).orElseThrow(() ->
                new EntityNotFoundException("존재하지 않는 주문 입니다.")));
    }

    public void changeDeliveryStatus(Long deliveryId, DeliveryStatus deliveryStatus) {

        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 배송 정보 입니다."));
        delivery.changeDeliveryStatus(deliveryStatus);
    }
}
