package song.sj.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import song.sj.entity.Member;
import song.sj.enums.Role;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
// 클래스는 사용자의 정보를 담고 있으며, Spring Security가 사용자 인증 및 권한 부여 작업을 할 때 UserDetails 객체를 통해 사용자 정보를 조회한다.
// 사용자 정보 제공: 사용자의 이름, 비밀번호, 권한 등을 제공하는 역할을 한다.
// 계정 상태 관리: 사용자의 계정이 활성화되었는지, 비활성화되었는지, 만료되었는지, 잠금 여부 등을 결정하는 역할을 한다.
public class CustomUserDetails implements UserDetails {

    private final Member member;

    @Override // role 값을 반환하는 메서드
    // GrantedAuthority는 스프링 시큐리티에서 권한(예: ROLE_USER, ROLE_ADMIN)을 나타내는 인터페이스.
    // getAuthorities()는 스프링 시큐리티가 사용자의 권한을 확인할 때 호출하는 메서드로, 이 메서드가 반환하는 Collection은 사용자가 가진 권한들의 모음.
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // ArrayList는 사용자가 가진 여러 권한을 담을 수 있도록 리스트 형태로 생성됨.
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole().toString();
            }
        });
        return collection;
    }

    public Member getMember() {
        return member;
    }

    public Long getMemberId() {
        return member.getId();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    // 계정이 만료되었는지 여부를 나타냄.
    // true이면 계정이 만료되지 않았고, false이면 계정이 만료되었음을 의미.
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    // 계정이 잠겨있는지 여부를 나타냄.
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // 사용자의 인증 정보(자격 증명, 예: 비밀번호)가 만료되었는지 여부를 나타냄.
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // 사용자가 활성화되었는지 여부를 나타냄.
    public boolean isEnabled() {
        return true;
    }
}
