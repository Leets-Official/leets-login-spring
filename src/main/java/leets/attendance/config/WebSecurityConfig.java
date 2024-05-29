package leets.attendance.config;

import leets.attendance.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {



    private final UserDetailService userService;

    //스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure(){
        return (web)->web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        //CORS
        http.cors(Customizer.withDefaults());

        //FormLogin, BasicHttp disable
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.logout(AbstractHttpConfigurer::disable);

        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //권한 규칙
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/login", "/users/register", "/users/check-duplicate-id").permitAll()
                .requestMatchers("/attendances", "/attendances/rates").authenticated() // "/users"에 대한 인증 필요
                .anyRequest().authenticated());

        // 보안 필터 체인 반환
        return http.build();
    }

    /*
    //스프링 시큐리티 기능 비활성화
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests()
                .requestMatchers("/login", "/signup", "/user").permitAll()  //특정 요청과 일치하는 url에 대해 누구나 접근 가능
                .anyRequest()   //위에서 설정한 url 이외의 요청에 대해
                .authenticated()    //별도의 인가는 필요하지 않지만 인증해 접근
                .and()
                .formLogin()    //폼 기반 로그인 설정 :
                .loginPage("/login")    //로그인 페이지로 경로 설정
                .defaultSuccessUrl("/articles") //로그인 완료됐을 때 이동할 경로
                .and()
                .logout()   //로그아웃 설정 :
                .logoutSuccessUrl("/login")// 로그아웃이 완료 되었을 떄 이동할 경로
                .invalidateHttpSession(true)    //로그아웃 이후 세션을 전체 삭제할지 여부
                .and()
                .csrf().disable()   //csrf 설정 비활성화
                .build();
    }

     */

    //인증 관리자 관련 설정
    /*
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService)
            throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class) //사용자 서비스 설정
                .userDetailsService(userService)    // 사용자 정보를 가져올 서비스를 설정. 이때 설정하는 서비스 클래스는 반드시 UserDetailsService를 상속받은 클래스여야 함
                .passwordEncoder(bCryptPasswordEncoder) // 비밀번호를 암호화하기 위한 인코더
                .and()
                .build();
    }*/



    //패스워드 인코더로 사용할 빈 등록
    /*
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    */







}
