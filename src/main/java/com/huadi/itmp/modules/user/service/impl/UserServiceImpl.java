package com.huadi.itmp.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huadi.itmp.common.dto.AuthenticationResultDTO;
import com.huadi.itmp.common.dto.TokenResult;
import com.huadi.itmp.core.redis.TokenManager;
import com.huadi.itmp.modules.user.entity.User;
import com.huadi.itmp.modules.user.entity.UserAuth;
import com.huadi.itmp.modules.user.enums.UserErrorCode;
import com.huadi.itmp.modules.user.exception.UserServiceException;
import com.huadi.itmp.modules.user.mapper.UserMapper;
import com.huadi.itmp.modules.user.service.IUserAuthService;
import com.huadi.itmp.modules.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huadi.itmp.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 胡学良
 * @since 2021-11-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private IUserAuthService userAuthService;

    private TokenManager tokenManager;

    @Autowired
    public UserServiceImpl(IUserAuthService userAuthService, TokenManager tokenManager) {
        this.userAuthService = userAuthService;
        this.tokenManager = tokenManager;
    }

    @Override
    public AuthenticationResultDTO login(String account, String password) throws UserServiceException {
        // 判断用户信息是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", account);
        User user = getOne(userQueryWrapper);
        if (null == user) {
            throw new UserServiceException(UserErrorCode.USER_NOT_FOUND);
        }

        // 判断账号密码是否匹配
        // 1.获取用户本地认证信息
        QueryWrapper<UserAuth> userAuthQueryWrapper = new QueryWrapper<>();
        userAuthQueryWrapper.eq("account", account);
        UserAuth userAuth = userAuthService.getOne(userAuthQueryWrapper);

        // 2.比较密码，如果不同则返回密码错误异常
        if (!userAuth.getPassword().equals(Md5Utils.encode(password))) {
            throw new UserServiceException(UserErrorCode.PASSWORD_ERROR);
        }

        // 生成并返回认证信息
        return makeAuthenticationResult(user.getUserId(), user.getRole());
    }


    /**
     * 获取token
     * @param userId 用户ID
     * @param role 角色信息
     * @return token
     */
    private AuthenticationResultDTO makeAuthenticationResult(String userId, Integer role) {
        AuthenticationResultDTO result = new AuthenticationResultDTO();
        TokenResult tokenResult = tokenManager.generateToken(userId, role);
        result.setUserId(userId);
        result.setRole(role);
        result.setAccessToken(tokenResult.getAccessToken());
        result.setRefreshToken(tokenResult.getRefreshToken());
        return result;
    }
}
