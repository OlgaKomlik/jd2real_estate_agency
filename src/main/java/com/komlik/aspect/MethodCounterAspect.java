package com.komlik.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@Component
@Aspect
public class MethodCounterAspect {

    private static final Logger logger = Logger.getLogger(MethodCounterAspect.class);

    private static final Map<String, Integer> methodCount = new HashMap<>();

    @Pointcut("execution(* com.komlik.repository.*.*(..))")
    public void methodCounterPointCut() {
    }

    @Around("methodCounterPointCut()")
    public Object logAroundMethodCounter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        if (methodCount.containsKey(methodName)) {
            methodCount.put(methodName, methodCount.get(methodName) + 1);
        } else {
            methodCount.put(methodName, 1);
        }
        Object proceed = proceedingJoinPoint.proceed();
        logger.info("Method " + proceedingJoinPoint.getSignature().getName() + "has been called " + methodCount.get(methodName) + "times");
        return proceed;
    }
}
