package com.huadi.itmp.core.aop;

import com.huadi.itmp.common.enums.ErrorCode;
import com.huadi.itmp.config.properties.ITMPProperties;
import com.huadi.itmp.core.api.Response;
import com.huadi.itmp.core.lock.RedisLockFactory;
import com.huadi.itmp.util.Md5Utils;
import com.huadi.itmp.util.SubjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.Lock;

/**
 * @author meteor
 */
@Aspect
@Component
@Slf4j
public class PreventDuplicateSubmission {

    private ITMPProperties ITMPProperties;
    private RedisLockFactory redisLockFactory;

    @Autowired
    public PreventDuplicateSubmission(RedisLockFactory redisLockFactory, ITMPProperties ITMPProperties) {
        this.redisLockFactory = redisLockFactory;
        this.ITMPProperties = ITMPProperties;

    }

    @Around(value = "pointCutPreventDuplicateAnnotation()")
    public Object processTx(ProceedingJoinPoint jp) throws Throwable {
        if (!ITMPProperties.getGlobalPreventDuplicate()) {
            log.info("global prevent duplicate closed.");
            return process(jp);
        } else {
            return jp.proceed();
        }
    }


    @Around(value = "pointCutRequestMapping() || pointCutGetMapping() || pointCutPutMapping() || pointCutPostMapping() || pointCutDeleteMapping()")
    public Object processGlobalPreventDu(ProceedingJoinPoint jp) throws Throwable {
        if (ITMPProperties.getGlobalPreventDuplicate()) {
            log.info("global prevent duplicate opened.");
            return process(jp);
        } else {
            return jp.proceed();
        }
    }


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointCutRequestMapping() {

    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void pointCutGetMapping() {

    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void pointCutPutMapping() {

    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void pointCutDeleteMapping() {

    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void pointCutPostMapping() {

    }

    @Pointcut("@annotation(com.huadi.itmp.core.annotation.PreventDuplicate)")
    public void pointCutPreventDuplicateAnnotation() {

    }

    private Object process(ProceedingJoinPoint jp) throws Throwable {
        String token = jp.getSignature().toString();
        token = SubjectUtils.getSubject().getIdentification() + ":" + token;
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        if (null != request) {
            token += request.getRequestURI();
        }
        Lock lock = redisLockFactory.createLock(Md5Utils.encode(token));
        if (lock.tryLock()) {
            try {
                return jp.proceed();
            } catch (Exception e) {
                throw e;
            } finally {
                lock.unlock();
            }
        } else {
            log.info("DuplicateSubmission:{}", token);
            return Response.error(ErrorCode.DUPLICATE_SUBMISSION);
        }
    }
}
