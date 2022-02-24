package com.huadi.itmp.core.redis;

import com.huadi.itmp.common.dto.TokenInfo;
import com.huadi.itmp.common.dto.TokenResult;
import com.huadi.itmp.common.helper.IRedisHelper;
import com.huadi.itmp.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author meteor
 */
@Component
public class TokenManager {

    private IRedisHelper redisHelper;

    private static final int DEFAULT_EXPIRE = 60 * 60 * 2;
    private static final int REFRESH_DEFAULT_EXPIRE = DEFAULT_EXPIRE * 2;

    private static final int ANDROID_DEFAULT_EXPIRE = 60 * 60 * 24 * 30;

    private static final int ANDROID_REFRESH_DEFAULT_EXPIRE = 60 * 60 * 24 * 10 * 30;

    @Autowired
    public void setRedisHelper(IRedisHelper redisHelper) {
        this.redisHelper = redisHelper;
    }

    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    public TokenResult generateToken(String userId, Integer role) {

        // 生成新token
        String newToken = UUIDUtils.randomToken();
        String newRefreshToken = UUIDUtils.randomToken();

        // 如果不是web端，则删除旧token和refresh_token
        removeTokenAndRefreshToken(userId);

        // 存储新token和新的refresh_token
        int tokenExpire, refreshTokenExpire;
        tokenExpire = DEFAULT_EXPIRE;
        refreshTokenExpire = REFRESH_DEFAULT_EXPIRE;

        redisHelper.set(RedisKey.TOKEN_TO_USER(newToken), TokenInfo.create(newToken, userId, role), tokenExpire);
        redisHelper.set(RedisKey.USER_TOKEN_INFO(userId), newToken, tokenExpire);

        redisHelper.set(RedisKey.REFRESH_TOKEN_TO_USER(newRefreshToken), TokenInfo.create(newRefreshToken, userId, role), refreshTokenExpire);
        redisHelper.set(RedisKey.USER_REFRESH_TOKEN_INFO(userId), newRefreshToken, refreshTokenExpire);
        return new TokenResult(newToken, newRefreshToken);
    }

    /**
     * 根据token获取token信息
     *
     * @param token
     * @return
     */
    public TokenInfo getTokenInfo(String token) {
        TokenInfo tokenInfo = redisHelper.get(RedisKey.TOKEN_TO_USER(token), TokenInfo.class);

        // 如果是web端，则刷新token
        redisHelper.expire(RedisKey.TOKEN_TO_USER(token), DEFAULT_EXPIRE);
        redisHelper.expire(RedisKey.USER_TOKEN_INFO(tokenInfo.getUserId()), DEFAULT_EXPIRE);
        return tokenInfo;
    }

    /**
     * 根据refreshToken获取refreshToken信息
     *
     * @param refreshToken
     * @return
     */
    public TokenInfo getRefreshTokenInfo(String refreshToken) {
        TokenInfo tokenInfo = redisHelper.get(RedisKey.REFRESH_TOKEN_TO_USER(refreshToken), TokenInfo.class);
        return tokenInfo;
    }


    /**
     * 删除token和refreshToken
     *
     * @param userId
     */
    public void removeTokenAndRefreshToken(String userId) {
        // 删除旧token和refresh_token
        String oldToken = redisHelper.get(RedisKey.USER_TOKEN_INFO(userId));
        String oldRefreshToken = redisHelper.get(RedisKey.USER_REFRESH_TOKEN_INFO(userId));
        if (null != oldToken) {
            redisHelper.del(
                    RedisKey.TOKEN_TO_USER(oldToken),
                    RedisKey.USER_TOKEN_INFO(userId)
            );
        }

        if (null != oldRefreshToken) {
            redisHelper.del(
                    RedisKey.REFRESH_TOKEN_TO_USER(oldRefreshToken),
                    RedisKey.USER_REFRESH_TOKEN_INFO(userId)
            );
        }
    }

    /**
     * 判断用户是否登录web端
     * @param userId
     * @return
     */

    public boolean isLoginToWeb(String userId) {
        return null != redisHelper.get(RedisKey.USER_TOKEN_INFO(userId));
    }

}
