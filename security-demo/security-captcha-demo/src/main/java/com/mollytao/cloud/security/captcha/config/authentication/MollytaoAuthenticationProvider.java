package com.mollytao.cloud.security.captcha.config.authentication;

import com.mollytao.cloud.security.captcha.exception.VerificationCodeException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MollytaoAuthenticationProvider extends DaoAuthenticationProvider {

    /**
     * @Description 构造方法注入UserDetailService和PasswordEncoder
     *
     * @author Stephen
     * @version 1.0
     * @date 2020/8/25 5:44
     * @param userDetailsService
     * @param passwordEncoder
     * @return
     */
    public MollytaoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        // 1、图形验证码的校验逻辑
        MollytaoWebAuthenticationDetails details = (MollytaoWebAuthenticationDetails) usernamePasswordAuthenticationToken.getDetails();
        String imageCode = details.getImageCode();
        String savedImageCode = details.getSavedImageCode();
        // 检验图形验证码
        if (StringUtils.isEmpty(imageCode) || StringUtils.isEmpty(savedImageCode) || !imageCode.equals(savedImageCode)) {
            throw new VerificationCodeException();
        }

        // 3、调用父类方法完成密码验证
        super.additionalAuthenticationChecks(userDetails, usernamePasswordAuthenticationToken);
    }

}
