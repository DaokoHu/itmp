package com.huadi.itmp.core.exception;

import com.huadi.itmp.common.enums.IErrorCode;

/**
 * 授权异常
 * @author meteor
 */
public class AuthorizationException extends ServiceException {
    public AuthorizationException(long errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public AuthorizationException(IErrorCode errorCode) {
        super(errorCode);
    }
}
