package com.ssm.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by ’‘√ŒΩ‹ on 2018/1/24.
 */
//@Component
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    //https://www.cnblogs.com/sevenlin/p/sevenlin_shiro20150924.html
//    @Override
//    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
//        WebUtils.getAndClearSavedRequest(request);
//        WebUtils.redirectToSavedRequest(request,response,"/index");
//        //this.issueSuccessRedirect(request, response);
//        return false;
//    }
}
