package com.huadi.itmp.config;

import com.huadi.itmp.core.lock.RedisLockFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author meteor
 */
@Configuration
public class RedisLockConfig {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public RedisLockConfig(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    RedisLockFactory redisLockFactory() {
        return new RedisLockFactory(redisTemplate);
    }
}
