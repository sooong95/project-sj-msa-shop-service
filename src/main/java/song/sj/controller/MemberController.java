package song.sj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.sj.entity.Member;
import song.sj.service.MemberService;

import java.net.http.HttpResponse;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> save(@Valid @RequestBody Member member) {
        memberService.save(member);

        return new ResponseEntity<>("회원 가입 성공!", CREATED);
    }
}
