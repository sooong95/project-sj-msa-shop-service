package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.Result;
import song.sj.dto.order.OrderHistoryDto;
import song.sj.dto.order.OrderSaveDto;
import song.sj.service.OrderQueryService;
import song.sj.service.OrderService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderQueryService orderQueryService;

    @PostMapping
    public ResponseEntity<String> orderSave(@RequestBody OrderSaveDto orderSaveDto) {

        orderService.orderSave(orderSaveDto);
        return new ResponseEntity<>("주문이 완료 되었습니다.", HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> orderCancel(@PathVariable("orderId") Long orderId) {

        orderService.orderCancel(orderId);
        return new ResponseEntity<>("주문이 취소 되었습니다.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Result<List<OrderHistoryDto>>> orderHistory() {

        return new ResponseEntity<>(orderQueryService.orderHistory(), HttpStatus.OK);
    }
}
