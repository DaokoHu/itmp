package com.huadi.itmp.core.lock;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.locks.Lock;

/**
 * @author meteor
 */
public class RedisLockFactory {
    private StringRedisTemplate redisTemplate;

    public RedisLockFactory(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Lock createLock(String lockKey) {
        return new RedisLock(redisTemplate, lockKey);
    }
}
