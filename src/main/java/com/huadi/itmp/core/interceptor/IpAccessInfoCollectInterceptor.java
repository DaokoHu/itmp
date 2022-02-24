package com.huadi.itmp.core.interceptor;

import com.huadi.itmp.common.helper.IRedisHelper;
import com.huadi.itmp.core.authentication.Subject;
import com.huadi.itmp.core.constant.SysConstants;
import com.huadi.itmp.core.flow.AccessInfo;
import com.huadi.itmp.core.flow.CollectAccessInfo;
import com.huadi.itmp.core.flow.ParamType;
import com.huadi.itmp.core.flow.TransmitType;
import com.huadi.itmp.core.redis.RedisKey;
import com.huadi.itmp.util.SubjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * IP访问统计拦截器
 * @author meteor
 */
@Slf4j
public class IpAccessInfoCollectInterceptor implements HandlerInterceptor {

    private IRedisHelper redisHelper;

    public IpAccessInfoCollectInterceptor(IRedisHelper redisHelper) {
        this.redisHelper = redisHelper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Subject currentSubject = SubjectUtils.getSubject();
        String ipAddress = currentSubject.getIpAddress();

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        int visitCount = getAndIncreaseVisitCount(ipAddress);
        Long latestVisitTime = getAndSetLatestVisitTime(ipAddress);
        /**
         * 判断该接口是需要注入访问信息
         * 如果目标接口方法上有Anonymous注解，则直接放行
         */
        CollectAccessInfo collectAccessInfo = searchInjectInfo(((HandlerMethod) handler).getMethod());
        if (null != collectAccessInfo) {
            log.info("found injectAccessInfo:" + collectAccessInfo.toString());
            String paramName = collectAccessInfo.paramName();
            ParamType paramType = collectAccessInfo.type();
            TransmitType transmitType = collectAccessInfo.transmitType();
            long timeout = collectAccessInfo.timeout();

            String paramValue = resolveParamValue(request, paramName, transmitType);
            AccessInfo accessInfo = getAccessInfo(ipAddress, paramType, paramValue, timeout);
            accessInfo.setVisitCount(visitCount);
            accessInfo.setLatestVisitTime(latestVisitTime);
            request.setAttribute(SysConstants.HTTP_ATTRIBUTE_ACCESS_INFO, accessInfo);
        }

        return true;
    }

    private AccessInfo getAccessInfo(String ipAddress, ParamType paramType, String paramValue, long timeout) {
        AccessInfo accessInfo = new AccessInfo();
        accessInfo.setIpAddress(ipAddress);
        if (!StringUtils.isEmpty(paramValue)) {
            accessInfo.setFieldVisitCount(getAndIncreaseFieldVisitCount(ipAddress, paramType, paramValue, timeout));
            accessInfo.setFieldLatestVisitTime(getAndSetFieldLatestVisitTime(ipAddress, paramType, paramValue, timeout));
        }
        return accessInfo;
    }

    /**
     * 获取字段的最后访问时间然后重新实则
     * @param ipAddress
     * @param paramType
     * @param paramValue
     * @return
     */
    private Long getAndSetFieldLatestVisitTime(String ipAddress, ParamType paramType, String paramValue, long timeout) {
        Long latestVisitTime = redisHelper.get(RedisKey.IP_FILED_LATEST_VISIT_TIME(ipAddress, paramType, paramValue), Long.class);
        redisHelper.set(RedisKey.IP_FILED_LATEST_VISIT_TIME(ipAddress, paramType, paramValue), System.currentTimeMillis(), timeout);
        return latestVisitTime;
    }

    /**
     * 获取字段的访问次数然后+1
     * @param ipAddress
     * @param paramType
     * @param paramValue
     * @return
     */
    private int getAndIncreaseFieldVisitCount(String ipAddress, ParamType paramType, String paramValue, long timeout) {
        Integer visitCount = redisHelper.get(RedisKey.IP_FILED_VISIT_COUNT(ipAddress, paramType, paramValue), Integer.class);
        if (null == visitCount) {
            redisHelper.set(RedisKey.IP_FILED_VISIT_COUNT(ipAddress, paramType, paramValue), 1, timeout);
            return 0;
        } else {
            redisHelper.set(RedisKey.IP_FILED_VISIT_COUNT(ipAddress, paramType, paramValue), visitCount + 1, timeout);
            return visitCount;
        }
    }

    /**
     * 获取访问次数然后对访问次数+1
     * @param ipAddress
     * @return
     */
    private Integer getAndIncreaseVisitCount(String ipAddress) {
        Integer visitCount = redisHelper.get(RedisKey.IP_VISIT_COUNT(ipAddress), Integer.class);
        if (null == visitCount) {
            redisHelper.set(RedisKey.IP_VISIT_COUNT(ipAddress), 1, 60 * 10);
            return 0;
        } else {
            redisHelper.set(RedisKey.IP_VISIT_COUNT(ipAddress), visitCount + 1, 60 * 10);
            return visitCount;
        }
    }

    /**
     * 获取最后访问时间然后更新最新访问时间
     * @param ipAddress
     * @return
     */
    private Long getAndSetLatestVisitTime(String ipAddress) {
        Long latestVisitTime = redisHelper.get(RedisKey.IP_VISIT_LATEST_VISIT_TIME(ipAddress), Long.class);
        redisHelper.set(RedisKey.IP_VISIT_LATEST_VISIT_TIME(ipAddress), System.currentTimeMillis(), 60 * 10);
        return latestVisitTime;
    }

    private String resolveParamValue(HttpServletRequest request, String paramName, TransmitType transmitType) {
        String paramValue = null;
        switch (transmitType) {
            case PATH:
                Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                paramValue = (String)pathVariables.get(paramName);
                break;
            case PARAM:
                paramValue = request.getParameter(paramName);
        }
        return paramValue;
    }

    private CollectAccessInfo searchInjectInfo(Method method) {
        for (Parameter parameter : method.getParameters()) {
            if (null != parameter.getAnnotation(CollectAccessInfo.class)) {
                return parameter.getAnnotation(CollectAccessInfo.class);
            }
        }
        return null;
    }
}
