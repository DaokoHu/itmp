package com.huadi.itmp.common.exception;

import com.huadi.itmp.common.enums.IErrorCode;
import com.huadi.itmp.core.exception.ServiceException;

/**
 * @author meteor
 */
public class AuthenticationServiceException extends ServiceException {
    public AuthenticationServiceException(long errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public AuthenticationServiceException(IErrorCode errorCode) {
        super(errorCode);
    }
}
