package com.imooc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class TimeAspect {
    private static final Logger logger = LoggerFactory.getLogger(TimeAspect.class);

//    @Pointcut

    @Around("execution(* com.imooc.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("TimeAspect start");
        logger.info(String.format("调用方法:%s.%s", joinPoint.getSignature().getDeclaringType().getName(), joinPoint.getSignature().getName()));
//        输出所有参数
        Arrays.asList(joinPoint.getArgs()).forEach(obj -> logger.info(String.format("arg: %s", obj)));
        Object obj = joinPoint.proceed();
        logger.info("start time=: " + new Date().getTime());
        logger.info(String .format("返回值: %s", obj));
        logger.info("TimeAspect end");
        return obj;
    }

}
