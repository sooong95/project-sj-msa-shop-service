package song.sj.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;
import song.sj.dto.CustomUserDetails;
import song.sj.dto.LoginDto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    // 요청에서 사용자 정보를 추출하고 인증을 시도하는 과정을 구현할 수 있다.

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginDto loginDto;

        // application/json 방식
        try {
            // ObjectMapper는 Jackson 라이브러리의 클래스. Jackson은 JSON 데이터를 자바 객체로 변환하거나, 그 반대로 변환할 때 사용된다.
            // 여기서 ObjectMapper를 통해 JSON 데이터를 LoginDto 객체로 변환.
            ObjectMapper objectMapper = new ObjectMapper();
            // request.getInputStream()은 HTTP 요청 바디를 읽기 위해 ServletInputStream 객체를 반환.
            // 이 스트림은 클라이언트가 보낸 데이터를 읽는 데 사용된다. 클라이언트가 JSON 데이터를 POST 방식으로 전송할 때 그 데이터는 요청의 바디에 담겨 있다.
            ServletInputStream inputStream = request.getInputStream();
            // StreamUtils.copyToString()은 스트림 데이터를 문자열로 변환하는 유틸리티 메서드입.
            // 여기서는 ServletInputStream에서 읽은 내용을 UTF-8 형식으로 문자열로 변환. 즉, 클라이언트가 보낸 JSON 데이터가 문자열 형태로 변환.
            String requestBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            // objectMapper.readValue()는 문자열로 된 JSON 데이터를 자바 객체로 변환하는 메서드입.
            // 여기서는 JSON 형식의 문자열인 requestBody 를 LoginDto 클래스의 객체로 변환.
            // 이 변환을 통해 클라이언트가 보낸 JSON 데이터가 자바에서 다룰 수 있는 LoginDto 객체가 된다.
            loginDto = objectMapper.readValue(requestBody, LoginDto.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // email, password 추출
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        // 스프링 시큐리티에서 email 과 password 를 검증하기 위해서는 token 에 담아야 한다.
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);
        // ^ authenticationManager 에 email 과 password 를 던져줄 때 dto 처럼 바구니 안에 담아서 던져줘야 하는데,
        // 그러한 역할이 UsernamePasswordAuthenticationToken
        // 세번째 인자는 role 값, 현재는 null 로 처리

        // token 을 담은 검증을 위한 AuthenticationManager 로 전달
        return authenticationManager.authenticate(authToken);
    }

    @Override
    // 로그인 성공시 실행하는 메서드 (여기서 jwt 를 발급하면 됨)
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        // 인증된 사용자 정보를 CustomUserDetails 객체로 가져온다. getPrincipal() 메서드는 인증된 사용자(주로 사용자 객체)를 반환한다.
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();

        Long memberId = customUserDetails.getMemberId();

        String email = customUserDetails.getUsername();

        // role 값을 뽑아내는 로직 ------------------------
        // authResult.getAuthorities(): 인증된 사용자의 권한 목록을 반환.
        // Iterator를 사용하여 권한 목록에서 첫 번째 권한을 가져온다. 여기서 GrantedAuthority는 권한 정보를 담는 인터페이스.
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();
        // role 값을 뽑아내는 로직 ------------------------

        String token = jwtUtils.createJwt(memberId, email, role, 36000 * 10000L);

        // Authorization 이라는 키에 담고, Bearer <-- jwt 데이터 인증 방식은 이것(꼭 뒤에 띄어쓰기 한번).
        response.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    // 로그인 실패시 실행하는 메서드
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        // 로그인 실패시 401 응답 코드 반환
        response.setStatus(401);
    }
}
