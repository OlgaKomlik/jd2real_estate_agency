package com.komlik.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MethodTimerAspect {

    private static final Logger logger = Logger.getLogger(MethodTimerAspect.class);

    @Pointcut("execution(* com.komlik.repository.*.*(..))")
    public void methodTimerPointcut() {
    }

    @Around("methodTimerPointcut()")
    public Object logAroundMethodTimer(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = proceedingJoinPoint.proceed();
        stopWatch.stop();
        logger.info("Method " + proceedingJoinPoint.getSignature().getName() + "worked in " + stopWatch.getLastTaskTimeMillis() + " ms");
        return proceed;
    }
}
