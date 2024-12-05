package song.sj.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.entity.Order;
import song.sj.enums.OrderStatus;
import song.sj.repository.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ShopOrderService {

    private final OrderRepository orderRepository;

    public void changeOrderStatus(Long orderId, OrderStatus orderStatus) {

        findOrder(orderId).changeOrderStatus(orderStatus);
    }

    private Order findOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 주문입니다."));
    }
}
