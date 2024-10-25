package song.sj.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import song.sj.entity.Address;

@Data
@AllArgsConstructor
public class MemberSearchDto implements MemberInfo{

    private Long id;
    private String username;
    private String email;
    private Address address;
}
