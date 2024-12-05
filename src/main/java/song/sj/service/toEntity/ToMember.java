package song.sj.service.toEntity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import song.sj.dto.member.MemberJoinDto;
import song.sj.dto.member.ShopMemberJoinDto;
import song.sj.entity.Member;

@Slf4j
@Getter
public class ToMember {

    public static Member toShopMemberEntity(ShopMemberJoinDto dto) {

        return Member.builder()
                .username(dto.getUsername())
                .businessRegistrationNumber(dto.getBusinessRegistrationNumber())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .address(dto.getAddress())
                .role(dto.getRole())
                .build();
    }

    public static Member toMemberEntity(MemberJoinDto dto) {

        return Member.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .address(dto.getAddress())
                .role(dto.getRole())
                .build();
    }
}
