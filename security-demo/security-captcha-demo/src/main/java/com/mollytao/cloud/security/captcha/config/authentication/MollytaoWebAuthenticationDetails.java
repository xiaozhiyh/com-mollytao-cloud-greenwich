package com.mollytao.cloud.security.captcha.config.authentication;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MollytaoWebAuthenticationDetails extends WebAuthenticationDetails {

    private String imageCode;

    private String savedImageCode;

    public String getImageCode() {
        return imageCode;
    }

    public String getSavedImageCode() {
        return savedImageCode;
    }

    /**
     * @Description 补充用户提交的验证码和session保存的验证码
     *
     * @author Stephen
     * @version 1.0
     * @date 2020/8/25 5:42
     * @param request
     * @return
     */
    public MollytaoWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.imageCode = request.getParameter("captcha");
        HttpSession session = request.getSession();
        this.savedImageCode = (String) session.getAttribute("captcha");
        if (!StringUtils.isEmpty(this.savedImageCode)) {
            // 随手清除验证码，不管是失败还是成功，所以客户端应在登录失败时刷新验证码
            session.removeAttribute("captcha");
        }
    }

}
