package com.huadi.itmp.common.exception;

/**
 * @author 胡学良
 * @date 2021-08-26 14:27
 **/
public class RedisOperationException extends RuntimeException{
    public RedisOperationException() {
    }

    public RedisOperationException(String message) {
        super(message);
    }

    public RedisOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
