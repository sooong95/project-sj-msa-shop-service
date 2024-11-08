package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.shop.ShopSaveDto;
import song.sj.service.ShopService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<String> save(@ModelAttribute ShopSaveDto shopSaveDto, @RequestParam("image") List<MultipartFile> files) {
        shopService.save(shopSaveDto, files);

        return new ResponseEntity<>("Shop 등록이 완료 되었습니다.", HttpStatus.CREATED);
    }

    @PatchMapping("/{shopId}")
    public ResponseEntity<String> updateShop(@PathVariable("shopId") Long shopId, @ModelAttribute ShopSaveDto shopSaveDto) {
        shopService.updateShop(shopId, shopSaveDto);

        return new ResponseEntity<>("shop 정보가 변경 되었습니다.", HttpStatus.OK);
    }

    @PostMapping("/{shopId}/images")
    public ResponseEntity<String> addShopImages(@PathVariable("shopId") Long id, @RequestParam("image") List<MultipartFile> files) {
        shopService.addImages(id, files);

        return new ResponseEntity<>("이미지가 성공적으로 등록 되었습니다.", HttpStatus.CREATED);
    }

    @DeleteMapping("/{shopId}/images")
    public ResponseEntity<String> deleteImages(@PathVariable("shopId") Long id) {
        shopService.deleteImage(id);

        return new ResponseEntity<>("이미지가 성공적으로 삭제 되었습니다.", HttpStatus.OK);
    }
}
