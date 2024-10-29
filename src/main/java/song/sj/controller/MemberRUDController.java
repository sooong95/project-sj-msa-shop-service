package song.sj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.Result;
import song.sj.dto.UpdateMemberDto;
import song.sj.dto.UpdateShopMemberDto;
import song.sj.dto.member.MemberInfo;
import song.sj.dto.member.MemberSearchDto;
import song.sj.entity.Member;
import song.sj.service.MemberQueryService;
import song.sj.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userInfo")
public class MemberRUDController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;

    private Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PatchMapping
    public ResponseEntity<String> updateMember(@RequestBody @Valid UpdateMemberDto updateMemberDto) {

        memberService.updateMember(updateMemberDto);
        return new ResponseEntity<>("회원 정보가 수정 되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMember(String password) {

        String email = authentication().getName();
        memberService.deleteMember(email, password);

        return new ResponseEntity<>("회원 탈퇴 완료", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<MemberSearchDto> findMember() {
        return new ResponseEntity<>((MemberSearchDto) memberService.findMember(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Result<List<MemberSearchDto>>> findMembers() {

        return new ResponseEntity<>(memberQueryService.findMembers(), HttpStatus.OK);
    }
}
