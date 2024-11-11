package song.sj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.member.MemberJoinDto;
import song.sj.dto.member.ShopMemberJoinDto;
import song.sj.enums.Role;
import song.sj.service.MemberService;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/join")
public class MemberJoinController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody MemberJoinDto memberJoinDto) {

        memberJoinDto.setRole(Role.ROLE_MEMBER);
        log.info("권한 확인={}", memberJoinDto.getRole());

        memberService.memberSave(memberJoinDto);

        return new ResponseEntity<>("MEMBER 가입 성공!", CREATED);
    }

    @PostMapping("/shop")
    public ResponseEntity<String> saveShop(@Valid @RequestBody ShopMemberJoinDto shopJoinDto) {

        shopJoinDto.setRole(Role.ROLE_SHOP);
        log.info("권한 확인={}", shopJoinDto.getRole());

        memberService.shopMemberSave(shopJoinDto);

        return new ResponseEntity<>("SHOP MEMBER 가입 성공", CREATED);
    }
}
