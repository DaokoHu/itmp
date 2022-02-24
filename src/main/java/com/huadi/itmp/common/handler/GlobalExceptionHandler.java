package com.huadi.itmp.common.handler;

import com.huadi.itmp.common.enums.ErrorCode;
import com.huadi.itmp.config.properties.ITMPProperties;
import com.huadi.itmp.core.api.Response;
import com.huadi.itmp.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 全局异常捕获器，捕获在Controller层未处理异常
 * @author meteor
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private ITMPProperties ITMPProperties;

    @Autowired
    public GlobalExceptionHandler(ITMPProperties ITMPProperties) {
        this.ITMPProperties = ITMPProperties;
    }

    @ExceptionHandler(value = Exception.class)
    public Response<String> exceptionHandler(HttpServletRequest request, Exception e) {
        boolean debugMode = ITMPProperties.getDebug();
        e.printStackTrace();
        if (e instanceof ServiceException) {
            ServiceException exception = (ServiceException) e;
            return Response.error(exception.getErrorCode(), exception.getErrorMsg());
        } else if (e instanceof HttpRequestMethodNotSupportedException){
            return Response.error(ErrorCode.OPERATION_FAILED.code(), e.getMessage());
        } {
            if (debugMode) {
                ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();
                e.printStackTrace(new PrintStream(byteBuff));
                String errTrace = byteBuff.toString();
                try {
                    byteBuff.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return Response.error(ErrorCode.OPERATION_FAILED.code(), errTrace);
            } else {
                return Response.error();
            }
        }
    }
}