package song.sj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.UpdateMemberDto;
import song.sj.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userInfo")
public class MemberRUDController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> updateMember(@RequestBody @Valid UpdateMemberDto updateMemberDto) {

        memberService.updateMember(updateMemberDto);

        return new ResponseEntity<>("회원 정보가 수정 되었습니다.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> check() {
        return new ResponseEntity<>("확인", HttpStatus.OK);
    }
}
