package song.sj.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import song.sj.jwt.JwtFilter;
import song.sj.jwt.JwtUtils;
import song.sj.jwt.LoginFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity // 스프링 시큐리티에서 사용하는 어노테이션. 웹 보안 기능을 활성화 하는 역할
@RequiredArgsConstructor
public class SecurityConfig {

    // AuthenticationManager 가 인자로 받을 AuthenticationConfiguration 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtils jwtUtils;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(cors -> cors.configurationSource(
                new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // 허용할 포트
                        configuration.setAllowedMethods(Collections.singletonList("*")); // 허용할 메서드 get, post... etc
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*")); // 허용할 시간
                        configuration.setMaxAge(3600L); // 허용할 시간

                        configuration.setExposedHeaders(Collections.singletonList("Authorization")); // 토큰을 넣어줄 Authorization 허용

                        return configuration;
                    }
                }
        ));

        // csrf disable
        http.csrf(AbstractHttpConfigurer::disable);
        // form 로그인 방식 disable
        http.formLogin(AbstractHttpConfigurer::disable);
        // basic 로그인 disable
        http.httpBasic(AbstractHttpConfigurer::disable);

        // 경로별 인가 작업
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/", "/join", "/join/shop").permitAll() // 해당 경로는 모든 권한 허용
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/shop", "/shop/**").hasRole("SHOP")// 해당 권한이 있는 사용자만 허용
                .anyRequest().authenticated() // 그 외 경로는 로그인한 사용자만 허용
        );

        // 특정 필드 앞에 필터 추가
        http.addFilterBefore(new JwtFilter(jwtUtils), LoginFilter.class);

        // addFilterAt => 기존에 있던 UsernamePasswordAuthenticationFilter 대신 커스텀한 LoginFilter 로 대체
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtils), UsernamePasswordAuthenticationFilter.class);

        // 세션 설정. jwt 는 세션을 stateless 상태로 관리.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
