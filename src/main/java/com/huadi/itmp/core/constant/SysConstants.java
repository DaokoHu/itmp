package com.huadi.itmp.core.constant;

/**
 * 系统常量
 *
 * @author meteor
 */
public class SysConstants {
    /**
     * 在jwt生成token里面存储subject的id的字段名称
     */
    public static final String CLAIM_SUBJECT_ID = "id";

    /**
     * 存储在HttpServletRequest里面当前登录的subject的键
     */
    public static final String HTTP_ATTRIBUTE_SUBJECT = "current_subject";

    /**
     * 存储在HttpServletRequest里面当前的用户访问信息
     */
    public static final String HTTP_ATTRIBUTE_ACCESS_INFO = "access_info";


    /**
     * 存储在HttpServletRequest里面的认证错误的信息
     */
    public static final String AUTHORIZATION_ERROR_CODE = "AUTHORIZATION_ERROR_CODE";

    public static final long DEFAULT_MAX_SIZE = 1024 * 1024 * 3;


    /**
     *
     */
    public static final String THREAD_LOCAL_KEY_PREVENT_DUPLICATE_REDIS_LOCK = "THREAD_LOCAL_KEY_PREVENT_DUPLICATE_REDIS_LOCK";
    public static final String SYS_ID = "SYS";

    public static final String SYS_NAME = "系统";
}
