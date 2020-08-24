package com.mollytao.cloud.security.captcha.config;

import com.mollytao.cloud.security.captcha.authentication.SecurityAuthenticationFailureHandler;
import com.mollytao.cloud.security.captcha.authentication.SecurityAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;


@EnableWebSecurity(debug = true)
public class MollytaoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> mollytaoWebAuthenticationDetailsSource;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 应用AuthenticationProvider
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/api/**").hasRole("ADMIN")
                .antMatchers("/user/api/**").hasRole("USER")
                .antMatchers("/app/api/**", "/captcha.jpg").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .authenticationDetailsSource(mollytaoWebAuthenticationDetailsSource)
                .failureHandler(new SecurityAuthenticationFailureHandler())
                .successHandler(new SecurityAuthenticationSuccessHandler())
                .loginPage("/myLogin.html")
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
