package com.huadi.itmp.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 胡学良
 * @date 2021-08-26 14:17
 **/
public class RequestUtils {
    private static final String STR_UN_KNOW = "unknown";

    /**
     * 获取访问者的IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || STR_UN_KNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || STR_UN_KNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || STR_UN_KNOW.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
