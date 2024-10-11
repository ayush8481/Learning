package com.example.learning.AOP;

import com.example.learning.Entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut(value = "execution(* com.example.learning.Service.StudentServiceImpl.*(..))")
    public void logging(){}

    @Around(value = "logging()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Around Advice Method Start : {}", proceedingJoinPoint.getSignature().getName());
        Object s =null;
        try {
            s =  proceedingJoinPoint.proceed();
        }
        catch(Exception e) {
            log.error("Around Advice Exception in Method : {} ", proceedingJoinPoint.getSignature(), e);
        }
        log.info("Around Advice Method End {} :", proceedingJoinPoint.getSignature().getName());
        return s;
    }
    //@Before(value = "execution(* com.example.learning.Service.StudentServiceImpl.*(..))")
    //@Before(value = "execution(* com.example.learning.Service.StudentServiceImpl.getStudent(..))")
    @Before(value = "logging()")
    public void methodStartDetails(JoinPoint joinPoint) {
        log.info("Before Advice Start Method : {}", joinPoint.getSignature().getName());
    }

    @After(value = "execution(* com.example.learning.Service.StudentServiceImpl.*(..))")
    public void methodEndDetails(JoinPoint joinPoint) {
        log.info("After Advice End Method : {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.example.learning.Service.StudentServiceImpl.*(..))")
    public void afterReturnAdvice(JoinPoint joinPoint) {
        log.info("AfterReturning Advice Successfully Completed Method : {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "execution(* com.example.learning.Service.StudentServiceImpl.*(..))", throwing = "e")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception e) {
        log.error("AfterThrowing Advice Exception in {} method :", joinPoint.getSignature().getName(), e);
    }
}
