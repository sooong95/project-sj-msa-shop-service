package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import song.sj.dto.order.OrderSaveDto;
import song.sj.service.OrderService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> orderSave(@RequestBody OrderSaveDto orderSaveDto) {

        orderService.orderSave(orderSaveDto);
        return new ResponseEntity<>("주문이 완료 되었습니다.", HttpStatus.CREATED);
    }
}
