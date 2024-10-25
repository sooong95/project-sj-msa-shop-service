package song.sj.dto.member;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import song.sj.entity.Address;
import song.sj.entity.Member;
import song.sj.enums.Role;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopMemberJoinDto {

    @NotEmpty(message = "이름은 필수입니다.")
    private String username;

    @NotEmpty
    private String shopName;
    @NotEmpty
    private String businessRegistrationNumber;

    @Column(unique = true)
    private String email;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // member, admin
    @Embedded
    @Valid
    private Address address;

    @Builder
    public ShopMemberJoinDto(String username, String shopName, String businessRegistrationNumber, String email, String password, Address address, Role role) {
        this.username = username;
        this.shopName = shopName;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public Role setRole(Role input) {
        this.role = input;
        return input;
    }

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .shopName(shopName)
                .businessRegistrationNumber(businessRegistrationNumber)
                .email(email)
                .password(password)
                .address(address)
                .role(role)
                .build();
    }
}