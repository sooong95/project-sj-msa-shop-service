package song.sj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.Result;
import song.sj.dto.item.FindItemDto;
import song.sj.dto.item.ItemSaveDto;
import song.sj.dto.item.ItemSearchConditionDto;
import song.sj.dto.item.SearchItemDto;
import song.sj.service.ItemQueryService;
import song.sj.service.ItemService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ItemQueryService itemQueryService;

    @PostMapping
    public ResponseEntity<String> saveItem(@Valid @ModelAttribute ItemSaveDto dto, @RequestParam("image") List<MultipartFile> files) {

        itemService.save(dto, files);

        return new ResponseEntity<>("정상적으로 상품이 등록 되었습니다.", HttpStatus.CREATED);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<String> updateItem(@Valid @RequestBody ItemSaveDto dto, @PathVariable("itemId") Long itemId) {

        itemService.updateItem(itemId, dto);

        return new ResponseEntity<>("상품 정보 변경 완료!", HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<FindItemDto> findItem(@PathVariable("itemId") Long id) {

        return new ResponseEntity<>(itemQueryService.findItem(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Result<List<SearchItemDto>>> searchItems(ItemSearchConditionDto dto, @RequestParam("categoryName") String categoryName) {

        return new ResponseEntity<>(itemQueryService.searchItems(dto, categoryName), HttpStatus.OK);
    }

    @GetMapping("/{itemId}/images")
    public ResponseEntity<?> findItemImage(@PathVariable("itemId") Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(itemQueryService.findItemImage(id));

    }

    @DeleteMapping("/deleteImage/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable("imageId") Long imageId) {

        itemService.deleteImage(imageId);

        return new ResponseEntity<>("이미지가 성공적으로 삭제 되었습니다.", HttpStatus.OK);
    }
}
