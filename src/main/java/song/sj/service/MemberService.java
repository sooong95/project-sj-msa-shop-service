package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.member.*;
import song.sj.dto.UpdateMemberDto;
import song.sj.dto.UpdateShopMemberDto;
import song.sj.entity.Member;
import song.sj.enums.Role;
import song.sj.repository.MemberRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    public Member getMemberFromJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return memberRepository.findByEmail(email);
    }

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

        Member member = memberRepository.findByEmail(loginMemberEmail());
        log.info("유저 이메일={}", loginMemberEmail());

        member.changeUsername(dto.getUsername());
        member.changePassword(dto.getNewPassword());
        member.changeAddress(dto.getAddress());
    }

    @Transactional
    public void updateShopMember(UpdateShopMemberDto dto) {

        Member member = memberRepository.findByEmail(loginMemberEmail());
        log.info("유저 이메일={}", loginMemberEmail());

        member.changeUsername(dto.getUsername());
        member.changePassword(dto.getNewPassword());
        member.changeShopName(dto.getShopName());
        member.changeBusinessRegistrationNumber(dto.getBusinessRegistrationNumber());
        member.changeAddress(dto.getAddress());
    }

    private static String loginMemberEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
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

    public MemberInfo findMember() {
        Member member = memberRepository.findByEmail(loginMemberEmail());
        log.info("로그인한 회원={}", member.getRole());

        if (member.getRole().equals(Role.ROLE_MEMBER))
            return new MemberSearchDto(member.getId(), member.getUsername(), member.getEmail(), member.getAddress());


        if (member.getRole().equals(Role.ROLE_SHOP))
            return new ShopMemberSearchDto(member.getId(), member.getUsername(), member.getEmail(), member.getShopName(), member.getBusinessRegistrationNumber(), member.getAddress());

        return null;
    }
}
