package song.sj.dto;

import lombok.Data;
import song.sj.enums.Role;

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
