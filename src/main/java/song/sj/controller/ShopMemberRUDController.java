package song.sj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.Result;
import song.sj.dto.UpdateShopMemberDto;
import song.sj.dto.member.ShopMemberSearchDto;
import song.sj.service.MemberQueryService;
import song.sj.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/userInfo")
public class ShopMemberRUDController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;

    @PatchMapping
    public ResponseEntity<String> updateShopMember(@RequestBody @Valid UpdateShopMemberDto dto) {

        memberService.updateShopMember(dto);
        return new ResponseEntity<>("회원 정보가 수정 되었습니다.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ShopMemberSearchDto> findMember() {
        return new ResponseEntity<>((ShopMemberSearchDto) memberService.findMember(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Result<List<ShopMemberSearchDto>>> findShopMembers() {

        return new ResponseEntity<>(memberQueryService.findShopMembers(), HttpStatus.OK);
    }
}
