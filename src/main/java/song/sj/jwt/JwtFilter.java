package song.sj.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import song.sj.dto.CustomUserDetails;
import song.sj.entity.Member;
import song.sj.enums.Role;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // request 에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.info("토큰 null");
            filterChain.doFilter(request, response);
            // 조건이 해당되면 메서드 종료
            return;
        }

        log.info("Authorization Now");
        // Bearer 제거 후 순수 토큰 획득
        String token = authorization.split(" ")[1];

        // 토큰 소멸 시간 검증
        if (jwtUtils.isExpired(token)) {

            log.info("토큰 만료");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰에서 email 과 role 획득
        String email = jwtUtils.getEmail(token);
        String role = jwtUtils.getRole(token);
        log.info("롤 값 확인={}", role);

        // member 를 생성해서 값 입력
        Member member = new Member();
        member.changeEmail(email);
        // 비밀번호는 DB 에서 조회하면 매번 요청이 올때마다 DB 에서 조회하게 되는 안좋은 상황이 발생하기 때문에 context holder 에 정확한 비밀번호를 입력할 필요 X
        // 그래서 임시 비밀번호
        member.changePassword("temppassword");
        member.changeRole(Role.valueOf(role));
        System.out.println("memberDto = " + member);

        // UserDetails 에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
