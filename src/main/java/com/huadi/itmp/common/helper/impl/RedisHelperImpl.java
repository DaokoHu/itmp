package com.huadi.itmp.common.helper.impl;

import com.huadi.itmp.common.exception.RedisOperationException;
import com.huadi.itmp.common.helper.IRedisHelper;
import com.huadi.itmp.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 胡学良
 * @date 2021-08-26 14:51
 **/
@Component
public class RedisHelperImpl implements IRedisHelper {
    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean expire(String key, long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void del(String... key) {
        if (key.length == 1) {
            redisTemplate.delete(key[0]);
        } else {
            redisTemplate.delete(Arrays.asList(key));
        }
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        String jsonValue = get(key);
        if (null == jsonValue) {
            return null;
        }
        try {
            if (type == Integer.class) {
                return (T) Integer.valueOf(jsonValue);
            }

            if (type == Long.class) {
                return (T) Long.valueOf(jsonValue);
            }

            if (type == String.class) {
                return (T) jsonValue;
            }

            return JsonUtils.toBean(jsonValue, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, Object value) throws RedisOperationException {
        try {
            if (value instanceof String) {
                redisTemplate.opsForValue().set(key, (String) value);
            } else {
                redisTemplate.opsForValue().set(key, JsonUtils.toJson(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisOperationException("数据存储失败");
        }
    }

    @Override
    public void set(String key, Object value, long seconds) throws RedisOperationException {
        try {
            if (value instanceof String) {
                redisTemplate.opsForValue().set(key, (String) value, seconds, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, JsonUtils.toJson(value), seconds, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisOperationException();
        }
    }

    @Override
    public long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public long decrement(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    @Override
    public void setAdd(String key, String... values) {
        redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public void setRemove(String key, String values) {
        redisTemplate.opsForSet().remove(key, values);
    }

    @Override
    public Set<String> setGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }
}
