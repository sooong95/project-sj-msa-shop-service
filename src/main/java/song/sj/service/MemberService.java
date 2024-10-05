package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.entity.Member;
import song.sj.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save(Member member) {

        if (checkEmailDuplicate(member.getEmail())) {
            throw new IllegalArgumentException("중복된 email 입니다.");
        }

        if (checkPassword(member.getPassword())) {
            throw new IllegalArgumentException("비밀번호는 4~12 자를 입력해주세요.");
        }
        String hashPassword = encodeBcrypt(member.getPassword());
        log.info("비밀번호 해시화={}", hashPassword);
        member.transPassword(hashPassword);
        memberRepository.save(member);
    }

    public boolean checkEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }

    public boolean checkPassword(String password) {
        return memberRepository.existsByPassword(password);
    }

    public String encodeBcrypt(String password) {
        int strength = 10;
        return new BCryptPasswordEncoder(strength).encode(password);
    }
}
