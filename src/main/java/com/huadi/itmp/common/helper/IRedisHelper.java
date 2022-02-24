package com.huadi.itmp.common.helper;

import com.huadi.itmp.common.exception.RedisOperationException;

import java.util.Set;

public interface IRedisHelper {
    /**
     * 设置缓存失效时间
     * @param key 键
     * @param seconds 时间(秒)
     * @return
     */
    boolean expire(String key, long seconds);

    /**
     * 根据 key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    long getExpire(String key);

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    boolean hasKey(String key);

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    void del(String... key);

    /**
     * 根据key获取缓存的对象
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    <T>T get(String key, Class<T> type);

    /**
     * 根据key获取缓存的字符串
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 存入对象
     * @param key 键
     * @param value 值
     */
    <T>void set(String key, T value) throws RedisOperationException;

    /**
     * 存入对象
     * @param key 键
     * @param value 值
     * @param seconds 有效期
     */
    void set(String key, Object value, long seconds) throws RedisOperationException;

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return
     */
    long increment(String key, long delta);

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return
     */
    long decrement(String key, long delta);

    /**
     * 向set里面存储数据
     * @param key
     * @param values
     */
    void setAdd(String key, String... values);

    /**
     * 移除set里面的数据
     * @param key
     * @param values
     */
    void setRemove(String key, String values);

    /**
     * 获取set里面的数据
     * @param key
     * @return
     */
    Set<String> setGet(String key);
}
