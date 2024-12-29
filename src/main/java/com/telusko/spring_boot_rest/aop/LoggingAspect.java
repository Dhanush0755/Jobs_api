package com.telusko.spring_boot_rest.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob(..))")   // returnType className.MethodName(arguments)
    public void logMethodCall(JoinPoint jp){
        LOGGER.info("Method called " + jp.getSignature().getName());
    }

    @After("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob(..))")   // returnType className.MethodName(arguments)
    public void logMethodExecuted(JoinPoint jp){
        LOGGER.info("Method executed " + jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob(..))")   // returnType className.MethodName(arguments)
    public void logMethodCrash(JoinPoint jp){
        LOGGER.info("Method crashed " + jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob(..))")   // returnType className.MethodName(arguments)
    public void logMethodReturned(JoinPoint jp){
        LOGGER.info("Method returned " + jp.getSignature().getName());
    }
}
