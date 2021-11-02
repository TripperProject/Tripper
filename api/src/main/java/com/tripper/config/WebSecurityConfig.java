package com.tripper.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // /about 요청에 대해서는 로그인을 요구함
                .antMatchers("/admin").authenticated()
                // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
                .antMatchers("/admin").hasRole("ADMIN")
                // 나머지 요청에 대해서는 로그인을 요구하지 않음
                .antMatchers("/console/**").permitAll()
                .anyRequest().permitAll();
//                .and()
//                // 로그인하는 경우에 대해 설정함
//                .formLogin()
//                // 로그인 페이지를 제공하는 URL을 설정함
//                .loginPage("/admin/login")
//                // 로그인 성공 URL을 설정함
//                .successForwardUrl("/admin/index")
//                // 로그인 실패 URL을 설정함
//                .failureForwardUrl("/admin/login")
//                .permitAll()
//                .and()
//                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

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
