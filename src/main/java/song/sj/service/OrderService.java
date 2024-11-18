package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.order.OrderSaveDto;
import song.sj.entity.Member;
import song.sj.entity.Order;
import song.sj.repository.OrderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;

    public void orderSave(OrderSaveDto orderSaveDto) {

     /*   orderRepository.save(Order.createOrder(memberService.getMemberFromJwt(),
                orderSaveDto.getItemList(), orderSaveDto.getShopList())); */
    }
}
