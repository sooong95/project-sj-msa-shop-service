package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.delivery.ChangeDeliveryStatusDto;
import song.sj.dto.delivery.DeliveryManagementSaveDto;
import song.sj.service.DeliveryService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/shop/{shopId}/delivery")
    public ResponseEntity<String> saveDelivery(@PathVariable("shopId") Long shopId, @RequestBody DeliveryManagementSaveDto dto) {

        deliveryService.saveDelivery(shopId, dto);
        return new ResponseEntity<>("배송 정보가 등록 되었습니다.", HttpStatus.CREATED);
    }

    @PostMapping("/shop/{shopId}/changeDeliveryStatus")
    public ResponseEntity<String> changeDeliveryStatus(@PathVariable("shopId") Long shopId,
                                                       @RequestBody ChangeDeliveryStatusDto dto
                                                       ) {

        deliveryService.changeDeliveryStatus(shopId, dto);
        return new ResponseEntity<>("배송 상태가 변경 되었습니다.", HttpStatus.OK);
    }
}
