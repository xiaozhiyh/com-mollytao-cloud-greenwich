package com.mollytao.cloud.security.captcha.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Administrator
 */
@Configuration
public class CaptchaConfig {

    public static final String KAPTCHA_IMAGE_WIDTH = "150";
    public static final String KAPTCHA_IMAGE_HEIGHT = "50";
    public static final String KAPTCHA_TEXT_PRODUCER_CHAR_STRING = "0123456789";
    public static final String KAPTCHA_TEXT_PRODUCER_CHAR_LENGTH = "4";

    /**
     *@user Administrator
     *@description TODO
     *@date 2020/8/19 12:48
     *@param 
     *@return 
     */
    @Bean
    public Producer captcha() {
        // 配置图形验证码的基本参数
        Properties properties = new Properties();
        // 图片宽度
        properties.setProperty("kaptcha.image.width", KAPTCHA_IMAGE_WIDTH);
        // 图片长度
        properties.setProperty("kaptcha.image.height", KAPTCHA_IMAGE_HEIGHT);
        // 字符集
        properties.setProperty("kaptcha.textproducer.char.string", KAPTCHA_TEXT_PRODUCER_CHAR_STRING);
        // 字符长度
        properties.setProperty("kaptcha.textproducer.char.length", KAPTCHA_TEXT_PRODUCER_CHAR_LENGTH);
        Config config = new Config(properties);
        // 使用默认的图形验证码实现，当然也可以自定义实现
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
