package com.huadi.itmp.core.interceptor;

import com.huadi.itmp.common.dto.TokenInfo;
import com.huadi.itmp.core.annotation.PreAuthorize;
import com.huadi.itmp.core.authentication.Subject;
import com.huadi.itmp.core.constant.HttpRequestHeaderNames;
import com.huadi.itmp.core.constant.SysConstants;
import com.huadi.itmp.core.enums.AuthorizationErrorCode;
import com.huadi.itmp.core.enums.SubjectType;
import com.huadi.itmp.core.exception.AuthorizationException;
import com.huadi.itmp.core.redis.TokenManager;
import com.huadi.itmp.util.RequestUtils;
import com.huadi.itmp.util.ThreadLocalMap;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token拦截器,根据token信息解析出subject
 * 如果没有携带认证信息或认证信息错误，则在HttpServlet上下文设置一个匿名的Subject，并设置认证的错误信息，然后对请求进行放行
 * 如果解析到正确的token，则在HttpServlet上下文设置一个经过认证的Subject，然后对请求进行放行
 *
 * @author meteor
 */
public class TokenInterceptor implements HandlerInterceptor {

    private static final String BEARER_AUTHORIZATION_START = "Bearer";

    private TokenManager tokenManager;

    public TokenInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader(HttpRequestHeaderNames.AUTHORIZATION);

        // 匿名subject
        Subject anonymousSubject = Subject.anonymous(RequestUtils.getIpAddr(request));

        // 1.未找到认证信息
        if (StringUtils.isEmpty(authorization)) {
            ThreadLocalMap.put(SysConstants.HTTP_ATTRIBUTE_SUBJECT, anonymousSubject);
            request.setAttribute(SysConstants.AUTHORIZATION_ERROR_CODE, AuthorizationErrorCode.NON_HEADER_AUTHORIZATION);
            return true;
        }

        // 2.找到认证信息但是认证信息非法
        if (!authorization.startsWith(BEARER_AUTHORIZATION_START) || authorization.length() <= BEARER_AUTHORIZATION_START.length() + 1) {
            ThreadLocalMap.put(SysConstants.HTTP_ATTRIBUTE_SUBJECT, anonymousSubject);
            request.setAttribute(SysConstants.AUTHORIZATION_ERROR_CODE, AuthorizationErrorCode.ILLEGAL_HEADER_AUTHORIZATION);
            return true;
        }

        // 3.找到认证信息，进行认证信息正确性校验,并生成Subject
        try {
            // 获取应有权限
            PreAuthorize annotation = ((HandlerMethod) handler).getMethod().getAnnotation(PreAuthorize.class);
            SubjectType[] type = annotation.value();
            // 获取token
            String token = authorization.substring(BEARER_AUTHORIZATION_START.length() + 1);
            TokenInfo tokenInfo = tokenManager.getTokenInfo(token);

            boolean param = false;
            for (SubjectType subjectType : type) {
                if (subjectType.equals(SubjectType.valueOf(tokenInfo.getRole()))) {
                    param = true;
                    break;
                }
            }

            if (!param) {
                throw new AuthorizationException(AuthorizationErrorCode.IDENTITY_INFORMATION);
            }

            if (null == tokenInfo) {
                throw new AuthorizationException(AuthorizationErrorCode.SIGN_VERIFY_FAILURE);
            }
            // 构建一个经过系统认证的subject
            Subject authenticatedSubject = Subject.authenticated(tokenInfo.getUserId(), RequestUtils.getIpAddr(request), tokenInfo.getRole());
            ThreadLocalMap.put(SysConstants.HTTP_ATTRIBUTE_SUBJECT, authenticatedSubject);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            // token校验失败
            ThreadLocalMap.put(SysConstants.HTTP_ATTRIBUTE_SUBJECT, anonymousSubject);

            request.setAttribute(SysConstants.AUTHORIZATION_ERROR_CODE, AuthorizationErrorCode.SIGN_VERIFY_FAILURE);
            return true;
        }

    }
}
