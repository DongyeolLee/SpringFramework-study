package com.dy.board.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class SampleAdvice {

    private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);

    @Before("execution(* com.dy.board.services.MessageService*.*(..))")
    public void startLog(JoinPoint jp) {
        logger.info("&&&&&&&&&&&&&&&&&&& aspect log");
        logger.info("&&&&&&&&&&&&&&&&&&& aspect log");
        logger.info("&&&&&&&&&&&&&&&&&&& aspect log");
        logger.info("&&&&&&&&&&&&&&&&&&& aspect log");

        logger.info(Arrays.toString(jp.getArgs()));
    }

    @Around("execution(* com.dy.board.services.MessageService*.*(..))")
    public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("********************************************************");

        long startTime = System.currentTimeMillis();
        logger.info(Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed();

        long endTime = System.currentTimeMillis();

        logger.info(pjp.getSignature().getName() + ":" + (endTime - startTime));
        logger.info("********************************************************");

        return result;
    }
}
