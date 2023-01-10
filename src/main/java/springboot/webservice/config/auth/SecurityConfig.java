package springboot.webservice.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import springboot.webservice.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests() // URL별 권한 관리 시작. 이게 있어야 andMatcher 사용 가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 전부 다
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 유저민
                .anyRequest().authenticated() // 나머지는 모두 인증된 사용자에게만
                .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 성공시 / 주소로 이동
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService)
                .and().and().build(); // 소셜 로그인 성공 시 후족 조치 실행 UserService 인터페이스의 구현체 등록
    }
}
