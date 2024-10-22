package song.sj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.UpdateMemberDto;
import song.sj.dto.UpdateShopMemberDto;
import song.sj.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/userInfo")
public class ShopMemberRUDController {

    private final MemberService memberService;

    private Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PatchMapping
    public ResponseEntity<String> updateShopMember(@RequestBody @Valid UpdateShopMemberDto dto) {

        memberService.updateShopMember(dto);
        return new ResponseEntity<>("회원 정보가 수정 되었습니다.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> check() {
        return new ResponseEntity<>("확인", HttpStatus.OK);
    }
}
