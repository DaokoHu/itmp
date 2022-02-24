package com.huadi.itmp.core.lock;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author meteor
 */
public class RedisLock implements Lock {
    private static final String LOCK_PREFIX = "LOCK:";
    private static final long timeout = 1000;
    private String lockKey;
    private StringRedisTemplate redisTemplate;

    public RedisLock(StringRedisTemplate redisTemplate, String lockKey) {
        this.lockKey = LOCK_PREFIX + lockKey;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void lock() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock() {
        long expireAt = System.currentTimeMillis() + timeout + 1;
        byte[] lock = lockKey.getBytes();
        return (boolean) redisTemplate.execute((RedisCallback) (connection) -> {
            boolean acquire = connection.setNX(lock, String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {
                byte[] value = connection.get(lock);
                if (!Objects.isNull(value) && value.length > 1) {
                    long expireTime = Long.parseLong(new String(value));
                    // 判断锁是否超时
                    if (System.currentTimeMillis() > expireTime) {
                        // 锁已超时
                        byte[] oldValue = connection.getSet(lock, String.valueOf(expireAt).getBytes());
                        return Arrays.equals(oldValue, value);
                    }
                }
            }
            return false;
        });
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unlock() {
        redisTemplate.delete(lockKey);
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}
