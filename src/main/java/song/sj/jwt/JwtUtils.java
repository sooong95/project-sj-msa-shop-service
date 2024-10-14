package song.sj.jwt;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
// 발급과 검증을 담당하는 클래스.
public class JwtUtils {

    // 시크릿 키를 사용할 객체.
    private SecretKey secretKey;

    // application.properties 에 있는 암호키를 불러옴.
    // jwt 는 string 타입의 키를 사용하지 않기 때문에, 그걸 기반으로 Secret 타입의 객체키를 만듦.
    public JwtUtils(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // email 검증
    // 이 코드는 **JWT (JSON Web Token)**에서 이메일 정보를 추출하는 메서드.
    // token에서 email 값을 꺼내오기 위해 JWT 라이브러리를 사용하고 있다.
    public String getEmail(String token) {
        log.info("페이로드 정보={}", Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload());
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    // 권한 검증
    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    // 만료 시간 검증
    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    // jwt 생성 - expiredMS -> 토큰이 살아있을 시간
    public String createJwt(String email, String role, Long expiredMs) {

        return Jwts.builder()
                .claim("email", email)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis())) // 현재 발행 시간
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) // 만료 시간 설, 만료 시간 = 현재 발행 시간 + 인자로 받은 토큰 생존 시간
                .signWith(secretKey) // 시그니처
                .compact();
    }
}
