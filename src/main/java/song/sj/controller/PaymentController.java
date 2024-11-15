package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import song.sj.service.PaymentService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam("amount") int amount) {

        log.info("입금 금액={}", amount);
        paymentService.deposit(amount);
        return new ResponseEntity<>("입금이 성공적으로 처리 되었습니다.", HttpStatus.OK);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(@RequestParam("amount") int amount) {

        log.info("입금 금액={}", amount);
        paymentService.withdrawal(amount);
        return new ResponseEntity<>("출금이 성곡적으로 처리 되었습니다.", HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<String> payment(@RequestParam("amount") int amount) {

        paymentService.payment(amount);
        return new ResponseEntity<>("결제가 성공적으로 처리 되었습니다.", HttpStatus.OK);
    }
}
