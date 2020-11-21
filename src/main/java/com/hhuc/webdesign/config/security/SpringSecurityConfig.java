package com.hhuc.webdesign.config.security;

import com.hhuc.webdesign.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Collections;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LoginSuccess loginSuccess;
    @Autowired
    LoginFailed loginFailed;
    @Autowired
    LogoutSuccess logoutSuccess;

    //数据库设置
    @Autowired
    private SecurityService securityService;

    @Autowired
    private NotLogin notLogin;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("porn");
        auth.userDetailsService(securityService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/test/user").hasAnyAuthority("USER")
                    .antMatchers("/my/*").hasAnyAuthority("USER")
                    .anyRequest().permitAll()
//                .and()
//                    .exceptionHandling().authenticationEntryPoint(notLogin)  //检测到未登录的时候设置返回json值
                .and()
                    .formLogin()
                    .successHandler(loginSuccess)
                    .failureHandler(loginFailed)
                .and()
                    .logout().logoutSuccessUrl("/security/logout");
        http
                .httpBasic()
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
//        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:8088/"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        configuration.setMaxAge(Duration.ofHours(1));
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html","/**.js","/**.css");
    }
}
