package song.sj.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.MemberJoinDto;
import song.sj.entity.Address;
import song.sj.entity.Member;
import song.sj.enums.Role;
import song.sj.repository.MemberRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberJoinService memberService;

    @Autowired
    MemberRepository memberRepository;

    Validator validator;

    Member member;
    MemberJoinDto memberJoinDto;


    @BeforeEach
    void testValidEmail() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void init() {
        memberJoinDto = MemberJoinDto.builder()
                .username(memberJoinDto.getUsername())
                .email(memberJoinDto.getEmail())
                .password(memberJoinDto.getPassword())
                .address(new Address("city", "street", "zipcode"))
                .role(memberJoinDto.setRole(Role.MEMBER))
                .build();
    }

    @Test
    void save() {

        // given
        Set<ConstraintViolation<MemberJoinDto>> validate = validator.validate(memberJoinDto);

        // when
        memberService.memberSave(memberJoinDto);
        Member findMember = memberRepository.findById(member.getId()).orElse(null);

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
}

