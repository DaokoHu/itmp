package com.huadi.itmp.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author meteor
 */
@Aspect
@Component
@Slf4j
public class CorsPreFlightRequestDetector {
    @Around(value = "execution(boolean *..*.preHandle(..))")
    public Object processTx(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) jp.getArgs()[0];
        if (request != null && CorsUtils.isPreFlightRequest(request)) {
            log.info("detected pre-flight-request:{}.", request.getRequestURL().toString());
            return true;
        } else {
            return jp.proceed();
        }
    }
}
