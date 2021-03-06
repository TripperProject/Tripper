package com.tripper.config.auth;

import com.tripper.config.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.csrf().disable().authorizeRequests()
                .antMatchers("/user/**").authenticated() //User
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") //Admin
                .antMatchers("/console/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
//                .loginPage("") // 로그인 페이지를 제공하는 URL을 설정함
//                .successForwardUrl("/admin/index") // 로그인 성공 URL을 설정함
//                .failureForwardUrl("/admin/login") // 로그인 실패 URL을 설정함
//                .permitAll();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
//        customAuthenticationFilter.setFilterProcessesUrl("/user/login");
//        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
//        customAuthenticationFilter.afterPropertiesSet();
//        return customAuthenticationFilter;
//    }

//    @Bean
//    public CustomLoginSuccessHandler customLoginSuccessHandler() {
//        return new CustomLoginSuccessHandler();
//    }
}
