package song.sj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import song.sj.dto.item.ItemSaveDto;
import song.sj.service.ItemService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody ItemSaveDto dto) {

        /*Integer size = Integer.valueOf(dto.getSize());*/

        itemService.save(dto);

        return new ResponseEntity<>("정상적으로 상품이 등록 되었습니다.", HttpStatus.CREATED);
    }
}
