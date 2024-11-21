package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.order.OrderHistoryDto;
import song.sj.service.MemberOrderQueryService;
import song.sj.service.MemberOrderService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/order")
public class ShopOrderController {

    private final MemberOrderService memberOrderService;
    private final MemberOrderQueryService memberOrderQueryService;

    @GetMapping("/{shopId}")
    public ResponseEntity<OrderHistoryDto> findOneShopOrder(@PathVariable("shopId") Long shopId) {

        return new ResponseEntity<>(memberOrderQueryService.findOneShopOrder(shopId), HttpStatus.OK);
    }
}
