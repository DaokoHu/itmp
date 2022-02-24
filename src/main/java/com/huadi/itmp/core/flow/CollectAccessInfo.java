package com.huadi.itmp.core.flow;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author meteor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface CollectAccessInfo {

    /**
     * 要访问的目标的类型
     * @return
     */
    ParamType type();

    /**
     * 要访问的目标的ID在请求中的名称
     * @return
     */
    String paramName();

    /**
     * 要访问的目标的ID在请求中的类型
     * @return
     */
    TransmitType transmitType() default TransmitType.PATH;

    long timeout() default 10 * 60;
}
