package com.pizza.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@ConditionalOnProperty(value = "logging.level.org.springframework.aop", havingValue = "ERROR")
public class LoggingAspectConfig {
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspectConfig.class);

    //AOP expression for which methods shall be intercepted
    @Around("execution(* com.pizza.service..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();

        //Log method execution time
        LOGGER.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }
}