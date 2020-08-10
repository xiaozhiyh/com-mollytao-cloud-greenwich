package com.mollytao.cloud.security.captcha.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Administrator
 */
public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException() {
        super("图形验证码校验失败");
    }

}
