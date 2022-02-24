package com.huadi.itmp.modules.user.exception;

import com.huadi.itmp.common.enums.IErrorCode;
import com.huadi.itmp.core.exception.ServiceException;

/**
 * @author 胡学良
 * @date 2021-11-08 11:18
 **/
public class UserServiceException extends ServiceException {

    public UserServiceException(long errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public UserServiceException(IErrorCode errorCode) {
        super(errorCode);
    }
}
