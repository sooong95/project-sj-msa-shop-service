package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.entity.Member;
import song.sj.repository.MemberRepository;
import song.sj.repository.SaveRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(Member member) {

        if (checkEmailDuplicate(member.getEmail())) {
            throw new IllegalArgumentException("중복된 email 입니다.");
        }

        member.transPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public boolean checkEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }
}
