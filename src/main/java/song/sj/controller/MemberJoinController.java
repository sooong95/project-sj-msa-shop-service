package song.sj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.MemberJoinDto;
import song.sj.dto.ShopJoinDto;
import song.sj.enums.Role;
import song.sj.service.MemberJoinService;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/join")
public class MemberJoinController {

    private final MemberJoinService memberService;

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody MemberJoinDto memberJoinDto) {

        memberJoinDto.setRole(Role.MEMBER);
        log.info("권한 확인={}", memberJoinDto.getRole());

        memberService.memberSave(memberJoinDto);

        return new ResponseEntity<>("MEMBER 가입 성공!", CREATED);
    }

    @PostMapping("/shop")
    public ResponseEntity<String> saveShop(@Valid @RequestBody ShopJoinDto shopJoinDto) {

        shopJoinDto.setRole(Role.SHOP);
        log.info("권한 확인={}", shopJoinDto.getRole());

        memberService.shopMemberSave(shopJoinDto);

        return new ResponseEntity<>("SHOP 가입 성공", CREATED);
    }
}
