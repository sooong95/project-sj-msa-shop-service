package song.sj.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import song.sj.TimeStamp;
import song.sj.enums.Role;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Shop extends TimeStamp /*implements UserInfo*/ {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;

    private Long businessRegistrationNumber;

    @NotEmpty(message = "이름은 필수입니다.")
    private String shopName;

    @Email
    @NotEmpty(message = "email은 필수입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z]+\\.com$",
            message = "유효하지 않는 email 입니다.")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.SHOP; // member, admin

    @Embedded
    @Valid
    private Address address;

    //@Override
    public void transPassword(String hashPassword) {
        this.password = hashPassword;
    }

}
