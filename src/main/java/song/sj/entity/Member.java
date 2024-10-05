package song.sj.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import song.sj.TimeStamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Member extends TimeStamp {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotEmpty(message = "이름은 필수입니다.")
    private String username;

    @Email
    @NotEmpty(message = "email은 필수입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z]+\\.com$",
            message = "유효하지 않는 email 입니다.")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    private String password;

    @Embedded
    @Valid
    private Address address;

    public void transPassword(String hashPassword) {
        this.password = hashPassword;
    }
}
