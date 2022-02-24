package com.huadi.itmp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 胡学良
 * @date 2021-08-26 14:18
 **/
public class ThreadLocalMap {
    private static final ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new MapThreadLocal();

    /**
     * 存值
     * @param key   the key
     * @param value the value
     */
    public static void put(String key, Object value) {
        getContextMap().put(key, value);
    }

    /**
     * 删除对象
     * @param key the key
     * @return the object
     */
    public static Object remove(String key) {
        return getContextMap().remove(key);
    }

    /**
     * 获取对象
     * @param key the key
     * @return the object
     */
    public static Object get(String key) {
        return getContextMap().get(key);
    }

    private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {
        /**
         * Initial value map.
         *
         * @return the map
         */
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>(10);
        }
    }

    /**
     * 取得thread context Map的实例。
     * @return thread context Map的实例
     */
    private static Map<String, Object> getContextMap() {
        return THREAD_CONTEXT.get();
    }

    /**
     * 清理线程所有被hold住的对象。以便重用！
     */
    public static void remove() {
        getContextMap().clear();
    }

}
