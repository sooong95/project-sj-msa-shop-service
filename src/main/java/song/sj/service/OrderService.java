package song.sj.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.order.OrderItemDto;
import song.sj.dto.order.OrderSaveDto;
import song.sj.dto.order.OrderShopDto;
import song.sj.entity.Order;
import song.sj.entity.OrderItem;
import song.sj.entity.OrderShop;
import song.sj.entity.Shop;
import song.sj.entity.item.Item;
import song.sj.repository.*;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderShopRepository orderShopRepository;
    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;
    private final MemberService memberService;

    public void orderSave(OrderSaveDto orderSaveDto) {

        for (OrderShopDto shop : orderSaveDto.getOrderShopList()) {
            Shop findShop = shopRepository.findById(shop.getShopId()).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 shop 입니다."));
            Order.addOrderShop(findShop);
            OrderShop orderShop = OrderShop.createOrderShop(findShop);
            orderShopRepository.save(orderShop);

            for (OrderItemDto item : shop.getOrderItemList()) {
                Item findItem = itemRepository.findById(item.getItemId()).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 아이템 입니다."));
                if (item.getQuantity() > findItem.getQuantity()) {
                    throw new RuntimeException("등록한 수량보다 요청 수량이 많습니다. 수량을 다시 확인해 주세요.");
                }
                orderItemRepository.save(OrderItem.createOrderItem(findItem, orderShop, item.getQuantity()));

            }
            Order order = Order.setMemberAndOrderStatus(memberService.getMemberFromJwt());
            orderShop.addOrder(order);
            orderRepository.save(order);
        }
    }
}
