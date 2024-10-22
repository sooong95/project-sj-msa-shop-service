package song.sj.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.MemberJoinDto;
import song.sj.dto.UpdateMemberDto;
import song.sj.entity.Address;
import song.sj.entity.Member;
import song.sj.enums.Role;
import song.sj.jwt.JwtFilter;
import song.sj.jwt.JwtUtils;
import song.sj.repository.MemberRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    Validator validator;

    Member member;
    MemberJoinDto memberJoinDto;
    MemberJoinDto memberJoinDto2;

    String memberEmail = "sooong@nnnn.com";
    String password = "password";


    @BeforeEach
    void testValidEmail() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void init() {
        memberJoinDto = MemberJoinDto.builder()
                .username("song")
                .email("song@nnnn.com")
                .password(password)
                .address(new Address("city", "street", "zipcode"))
                .role(Role.MEMBER)
                .build();

        memberJoinDto2 = MemberJoinDto.builder()
                .username("song")
                .email(memberEmail)
                .password(password)
                .address(new Address("city", "street", "zipcode"))
                .role(Role.MEMBER)
                .build();

        memberRepository.save(memberJoinDto2.toEntity());
    }

    @Test
    void save() {

        // given
        Set<ConstraintViolation<MemberJoinDto>> validate = validator.validate(memberJoinDto);

        // when
        memberService.memberSave(memberJoinDto);
        Member findMember = memberRepository.findByEmail(memberJoinDto.getEmail());

        // then
        Assertions.assertThat(memberJoinDto.getEmail()).isEqualTo(findMember.getEmail());

    }

    @Test
    void checkDuplicateEmail() {

        memberService.memberSave(memberJoinDto);
        MemberJoinDto memberJoinDto2 = MemberJoinDto.builder()
                .username(memberJoinDto.getUsername())
                .email(memberJoinDto.getEmail())
                .password(memberJoinDto.getPassword())
                .address(new Address("city", "street", "zipcode"))
                .role(memberJoinDto.setRole(Role.MEMBER))
                .build();

        Assertions.assertThatThrownBy(() -> memberService.memberSave(memberJoinDto2)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateMember() {

        // given
        UpdateMemberDto updateMemberDto = new UpdateMemberDto();
        updateMemberDto.setUsername("update");
        updateMemberDto.setNewPassword("updatePassword");
        updateMemberDto.setAddress(new Address("updateCity", "updateStreet", "updateZipcode"));

        // when

        Member findMember = memberService.findMember(memberJoinDto2.getEmail());

        findMember.changeUsername(updateMemberDto.getUsername());
        findMember.changePassword(updateMemberDto.getNewPassword());
        findMember.changeAddress(updateMemberDto.getAddress());

        Member updateMember = memberService.findMember(memberJoinDto2.getEmail());
        // then
        assertEquals(updateMember.getUsername(), updateMemberDto.getUsername());
        assertEquals(updateMember.getPassword(), updateMemberDto.getNewPassword());
        assertEquals(updateMember.getAddress(), updateMemberDto.getAddress());
    }

    @Test
    void deleteMemberSuccess() {

        // given

        // when
        memberService.deleteMember(memberEmail, password);

        // then
        assertEquals(memberService.findMember(memberEmail), null);
    }

    @Test
    void deleteMemberWrongPassword() {

        // given
        String wrongPassword = "wrongPassword";

        // when
        memberService.deleteMember(memberEmail, wrongPassword);

        // then

    }
}

