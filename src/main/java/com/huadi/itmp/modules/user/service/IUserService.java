package com.huadi.itmp.modules.user.service;

import com.huadi.itmp.common.dto.AuthenticationResultDTO;
import com.huadi.itmp.modules.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huadi.itmp.modules.user.exception.UserServiceException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡学良
 * @since 2021-11-08
 */
public interface IUserService extends IService<User> {

    /**
     * 账号密码登录
     *
     * @param account 账号
     * @param password 密码
     * @return 用户认证信息
     */
    AuthenticationResultDTO login(String account, String password) throws UserServiceException;
}
