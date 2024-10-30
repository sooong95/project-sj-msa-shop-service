package song.sj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.Result;
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
    public ResponseEntity<String> save(@Valid @RequestBody ItemSaveDto dto, @RequestParam("file") MultipartFile file) {


        itemService.save(dto, file);

        return new ResponseEntity<>("정상적으로 상품이 등록 되었습니다.", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateItem(@Valid @RequestBody ItemSaveDto dto, @PathVariable("id") Long itemId) {

        itemService.updateItem(itemId, dto);

        return new ResponseEntity<>("상품 정보 변경 완료!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Result<List<SearchItemDto>>> searchItems(ItemSearchConditionDto dto, @RequestParam("categoryName") String categoryName) {

        return new ResponseEntity<>(itemQueryService.searchItems(dto, categoryName), HttpStatus.OK);
    }
}
