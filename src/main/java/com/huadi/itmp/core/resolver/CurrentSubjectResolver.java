package com.huadi.itmp.core.resolver;

import com.huadi.itmp.core.annotation.CurrentSubject;
import com.huadi.itmp.core.constant.SysConstants;
import com.huadi.itmp.util.ThreadLocalMap;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author meteor
 */
public class CurrentSubjectResolver implements HandlerMethodArgumentResolver{

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentSubject.class);
    }


    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return ThreadLocalMap.get(SysConstants.HTTP_ATTRIBUTE_SUBJECT);
    }
}
