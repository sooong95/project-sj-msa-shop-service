package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.MemberJoinDto;
import song.sj.dto.ShopMemberJoinDto;
import song.sj.dto.UpdateMemberDto;
import song.sj.dto.UpdateShopMemberDto;
import song.sj.entity.Member;
import song.sj.repository.MemberRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void memberSave(MemberJoinDto dto) {

        if (checkDuplicateEmail(dto.getEmail())) {
            throw new IllegalArgumentException("중복된 email 입니다.");
        }

        Member member = dto.toEntity();
        member.transPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        log.info("멤버 비밀번호={}", member.getPassword());
        memberRepository.save(member);
    }

    @Transactional
    public void shopMemberSave(ShopMemberJoinDto dto) {

        if (checkDuplicateEmail(dto.getEmail())) {
            throw new IllegalArgumentException("중복된 email 입니다.");
        }

        Member shopMember = dto.toEntity();
        shopMember.transPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        log.info("멤버 비밀번호={}", shopMember.getPassword());
        memberRepository.save(shopMember);
    }

    private boolean checkDuplicateEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public Member findMember(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional
    public void updateMember(UpdateMemberDto dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email);
        log.info("유저 이메일={}", email);

        member.changeUsername(dto.getUsername());
        member.changePassword(dto.getNewPassword());
        member.changeAddress(dto.getAddress());
    }

    @Transactional
    public void updateShopMember(UpdateShopMemberDto dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email);
        log.info("유저 이메일={}", email);

        member.changeUsername(dto.getUsername());
        member.changePassword(dto.getNewPassword());
        member.changeShopName(dto.getShopName());
        member.changeBusinessRegistrationNumber(dto.getBusinessRegistrationNumber());
        member.changeAddress(dto.getAddress());
    }

    @Transactional
    public void deleteMember(String email, String password) {

        Member findMember = memberRepository.findByEmail(email);

        if (findMember.getPassword().equals(password)) {
            memberRepository.delete(findMember);
        } else {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }
}
