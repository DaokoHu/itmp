package com.huadi.itmp.core.annotation;

import com.huadi.itmp.core.enums.SubjectType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在接口上加入此注解表示该接口需要用户认证
 * @author 胡学良
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PreAuthorize {
    SubjectType[] value();
}
