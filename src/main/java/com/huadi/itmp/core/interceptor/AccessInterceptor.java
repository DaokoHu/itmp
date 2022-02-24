package com.huadi.itmp.core.interceptor;

import com.huadi.itmp.common.exception.AuthenticationServiceException;
import com.huadi.itmp.core.annotation.Anonymous;
import com.huadi.itmp.core.authentication.Subject;
import com.huadi.itmp.core.constant.SysConstants;
import com.huadi.itmp.core.enums.AuthorizationErrorCode;
import com.huadi.itmp.util.ThreadLocalMap;
import lombok.extern.java.Log;
import org.apache.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 访问拦截器
 * 判断当前访问者是否经过认证
 * 若没有经过认证，则阻止继续访问，并返回错误信息
 * 若经过认证，则直接放行
 * @author meteor
 */
@Log
public class AccessInterceptor implements HandlerInterceptor {


    public AccessInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 判断该接口是否允许匿名访问
         * 如果目标接口方法上有Anonymous注解，则直接放行
         */
        boolean allowAnonymous = false;
        if (((HandlerMethod) handler).hasMethodAnnotation(Anonymous.class)) {
            allowAnonymous = true;
        }

        // 1.若不需要认证或者用户认证成功则直接放行
        if (allowAnonymous) {
            return true;
        }

        Subject currentSubject = (Subject) ThreadLocalMap.get(SysConstants.HTTP_ATTRIBUTE_SUBJECT);

        // 2.目标接口需要认证且经过认证，直接放行
        if (null != currentSubject && currentSubject.isAuthenticated()) {
            return true;
        } else {
            // 3.目标接口需要认证且未经过认证，则抛出异常
            AuthorizationErrorCode authorizationErrorCode = (AuthorizationErrorCode) request.getAttribute(SysConstants.AUTHORIZATION_ERROR_CODE);
            if (null == authorizationErrorCode) {
                authorizationErrorCode = AuthorizationErrorCode.NON_HEADER_AUTHORIZATION;
            }

            // 设置http响应状态
            int httpStatus;
            switch (authorizationErrorCode) {
                case NON_HEADER_AUTHORIZATION:
                    httpStatus = HttpStatus.SC_OK;
                    break;
                case ILLEGAL_HEADER_AUTHORIZATION:
                    httpStatus = HttpStatus.SC_OK;
                    break;
                case SIGN_VERIFY_FAILURE:
                    httpStatus = HttpStatus.SC_OK;
                    break;
                default:
                    httpStatus = HttpStatus.SC_OK;
            }
            response.setStatus(httpStatus);

            // 抛出异常
            throw new AuthenticationServiceException(authorizationErrorCode);
        }
    }
}
