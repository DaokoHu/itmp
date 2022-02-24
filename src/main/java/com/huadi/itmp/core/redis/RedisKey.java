package com.huadi.itmp.core.redis;

import com.huadi.itmp.core.flow.ParamType;

/**
 * Redis键常量
 * @author meteor
 */
public final class RedisKey {

    private static final String KEY_PREFIX_MOBILE_VERIFY_CODE = "verify_code_mobile:";

    private static final String KEY_PREFIX_EMAIL_VERIFY_CODE = "verify_code_email:";

    private static final String KEY_PREFIX_EMAIL_ACTIVE_CODE = "email_active_code:";

    private static final String KEY_PREFIX_USER_FOLLOWED = "user_followed:";
    private static final String KEY_PREFIX_USER_FANS = "user_fans:";

    /**
     * 手机验证码
     * @param mobile
     * @return
     */
    public static String MOBILE_VERIFY_CODE(String mobile) {
        return KEY_PREFIX_MOBILE_VERIFY_CODE + mobile;
    }

    /**
     * 邮箱验证码
     * @param email
     * @return
     */
    public static String EMAIL_VERIFY_CODE(String email) {
        return KEY_PREFIX_EMAIL_VERIFY_CODE + email;
    }

    /**
     * 邮箱激活码
     * @param activeCode
     * @return
     */
    public static String EMAIL_ACTIVE_CODE(String activeCode) {
        return KEY_PREFIX_EMAIL_ACTIVE_CODE + activeCode;
    }

    /**
     * 用户关注的人
     * @param userId
     * @return
     */
    public static String USER_FOLLOWED(String userId) {
        return KEY_PREFIX_USER_FOLLOWED + userId;
    }

    /**
     * 用户的所有粉丝
     * @param userId
     * @return
     */
    public static String USER_FANS(String userId) {
        return KEY_PREFIX_USER_FANS + userId;
    }

    public static String IP_VISIT_COUNT(String ipAddress) {
        return "IP_VISIT_COUNT:" + ipAddress;
    }

    public static String IP_VISIT_LATEST_VISIT_TIME(String ipAddress) {
        return "IP_VISIT_LATEST_VISIT_TIME:" + ipAddress;
    }

    public static String IP_FILED_VISIT_COUNT(String ipAddress, ParamType paramType, String paramValue) {
        return "IP_FILED_VISIT_COUNT:" + ipAddress + ":" + paramType.name() + ":" + paramValue;
    }

    public static String IP_FILED_LATEST_VISIT_TIME(String ipAddress, ParamType paramType, String paramValue) {
        return "IP_FILED_LATEST_VISIT_TIME:" + ipAddress + ":" + paramType.name() + ":" + paramValue;
    }

    public static String USER_NICK_GEN(String datePrefix) {
        return "USER_NICK_GEN:" + datePrefix;
    }

    public static String USER_TOKEN_INFO(String userId) {
        return "USER_TO_TOKEN:" + userId;
    }

    public static String TOKEN_TO_USER(String token) {
        return "TOKEN_TO_USER:" + token;
    }

    public static String USER_REFRESH_TOKEN_INFO(String userId) {
        return "USER_TO_REFRESH_TOKEN:" + userId;
    }

    public static String REFRESH_TOKEN_TO_USER(String token) {
        return "REFRESH_TOKEN_TO_USER:" + token;
    }
}
