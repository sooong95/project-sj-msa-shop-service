package song.sj.dto;

import jakarta.persistence.Embedded;
import song.sj.TimeStamp;
import song.sj.entity.Address;

public class UpdateMemberDto extends TimeStamp {

    private String username;

    private String password;

    @Embedded
    private Address address;
}
