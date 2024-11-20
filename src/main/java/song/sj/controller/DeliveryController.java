package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import song.sj.dto.delivery.DeliverySaveDto;
import song.sj.service.DeliveryService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<String> saveDelivery(DeliverySaveDto dto) {

        deliveryService.saveDelivery(dto);
        return new ResponseEntity<>("배송 정보가 등록 되었습니다.", HttpStatus.CREATED);
    }
}
