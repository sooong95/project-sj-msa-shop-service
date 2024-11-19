package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.delivery.DeliverySaveDto;
import song.sj.repository.DeliveryRepository;
import song.sj.repository.OrderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService {

    private DeliveryRepository deliveryRepository;
    private OrderRepository orderRepository;

    public void saveDelivery(DeliverySaveDto dto) {


    }
}
