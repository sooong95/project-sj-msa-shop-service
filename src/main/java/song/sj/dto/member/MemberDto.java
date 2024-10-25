package song.sj.dto.member;

import lombok.Data;

@Data
public class MemberDto {

    private String email;
    private String password;
    private String role;

    @Override
    public String toString() {
        return "MemberDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
