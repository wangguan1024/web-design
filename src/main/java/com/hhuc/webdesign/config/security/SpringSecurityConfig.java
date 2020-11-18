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
    private JsonEntryPoint jsonEntryPoint;



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
                .and()
                    .exceptionHandling().authenticationEntryPoint(jsonEntryPoint)  //检测到未登录的时候设置返回json值
                .and()
                    .formLogin()
                    .successHandler(loginSuccess)
                    .failureHandler(loginFailed)
                .and()
                    .logout().logoutSuccessUrl("/security/logout");
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html","/**.js","/**.css");
    }
}
