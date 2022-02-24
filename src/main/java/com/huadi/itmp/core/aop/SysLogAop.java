package com.huadi.itmp.core.aop;

import com.huadi.itmp.core.authentication.Subject;
import com.huadi.itmp.core.constant.SysConstants;
import com.huadi.itmp.core.enums.SubjectType;
import com.huadi.itmp.util.ThreadLocalMap;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Classname SysLogAop
 * @Description TODO 用来记录系统的操作，并做日志
 * @Date 2021/9/28 11:16
 * @Created by 杨小鹏：【241960695@qq.com】
 */
@Component
@Slf4j
@Aspect
public class SysLogAop {

    // 定义切面表达式
    @Pointcut("execution(* com.huadi.itmp.modules.*.service.impl.*.*(..))")
    public void logPoint() {
    }

    // 定义前置通知
    public void myBefore(ProceedingJoinPoint pj) throws Throwable {

    }

    @Around("logPoint()")
    public Object aroundLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;


        log.info("------>进行操作日志记录.....");
        // Object target = pj.getTarget();  // 获取执行的目标类的地址
        // Object aThis = pj.getThis();     // 获取执行的目标类的地址
        String currMethod = printCurrLog(joinPoint);

        result = joinPoint.proceed();

        log.info("---------->结束执行" + currMethod + "()方法");


        return result;
    }

    public String printCurrLog(ProceedingJoinPoint joinPoint){
        Subject subject = (Subject) ThreadLocalMap.get(SysConstants.HTTP_ATTRIBUTE_SUBJECT);;

        // 获取当前执行的方法
        String currMethod = joinPoint.getSignature().getName();

        // 获取执行当前操作的用户id
        Integer currUserId = (Integer)subject.getId();

        // 获取当前用户的角色
        SubjectType type = subject.getType();

        switch (type){
            case ANONYMOUS:
                log.info("------>开始执行" + currMethod + "()方法，角色为：匿名用户");
                break;
            case TEACHING_MANAGEMENT_SUPERVISOR:
                log.info("------>开始执行" + currMethod + "()方法，角色为：教学管理主管" + "，用户ID为：" + currUserId);
                break;
            case TEACHING_AND_RESEARCH_SECTION_PRINCIPAL:
                log.info("------>开始执行" + currMethod + "()方法，角色为：专业教研室负责人" + "，用户ID为：" + currUserId);
                break;
            case IMPLEMENTATION_ENGINEER:
                log.info("------>开始执行" + currMethod + "()方法，角色为：实施工程师" + "，用户ID为：" + currUserId);
                break;
            case MARKETING_PRINCIPAL:
                log.info("------>开始执行" + currMethod + "()方法，角色为：市场人员" + "，用户ID为：" + currUserId);
                break;
            case CAREER_GUIDANCE_PRINCIPAL:
                log.info("------>开始执行" + currMethod + "()方法，角色为：就业辅导专员" + "，用户ID为：" + currUserId);
                break;
            case STUDENT_MANAGEMENT_PRINCIPAL:
                log.info("------>开始执行" + currMethod + "()方法，角色为：学生管理专员" + "，用户ID为：" + currUserId);
                break;
            default:
                log.info("------>开始执行" + currMethod + "()方法，角色为：学生" + "，用户ID为：" + currUserId);
                break;

        }
        return currMethod;
    }
}
