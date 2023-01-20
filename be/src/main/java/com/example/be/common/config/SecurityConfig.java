package com.example.be.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    /*
    * csrf는 보안설정 중 POST방식으로 값을 전송할 때 token을 사용해야 하는 보안 설정
    * */
    http
        .csrf().disable();

    /*
    * 프로젝트에는 따로 권한을 두지 않을 것이므로 누구나 접근할 수 있도록 함
    * */
    http
        .authorizeRequests()
            .antMatchers("/api/**").permitAll();

    /*
    * 로그인 폼 설정
    * logout의 경우, Spring Security가 자동으로 처리한다
    * */
    http
        .formLogin()
        .loginPage("/api/member/login")
        .defaultSuccessUrl("/api/home")
        .usernameParameter("email") // 로그인 폼에서
        .failureUrl("/api/member/login/error")
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/api/member/logout"))
        .logoutSuccessUrl("/")
        ;

    return http.build();
  }

  /*
  * 비밀번호를 그대로 저장하지 않고 해시함수를 이용하여 암호화 처리
  * */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
