package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.order.OrderHistoryDto;
import song.sj.enums.OrderStatus;
import song.sj.service.MemberOrderQueryService;
import song.sj.service.MemberOrderService;
import song.sj.service.ShopOrderService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/order")
public class ShopOrderController {

    private final MemberOrderQueryService memberOrderQueryService;
    private final ShopOrderService shopOrderService;

    @GetMapping("/{shopId}")
    public ResponseEntity<OrderHistoryDto> findOneShopOrder(@PathVariable("shopId") Long shopId) {

        return new ResponseEntity<>(memberOrderQueryService.findOneShopOrder(shopId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> changeOrderStatus(@RequestParam("orderId") Long orderId,
                                                    @RequestParam("orderStatus") OrderStatus orderStatus) {
        shopOrderService.changeOrderStatus(orderId, orderStatus);
        return new ResponseEntity<>("주문 상태가 변경 되었습니다.", HttpStatus.OK);
    }
}
