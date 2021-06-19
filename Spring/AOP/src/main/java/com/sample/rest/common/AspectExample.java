package com.sample.rest.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectExample {
    @Before("execution(* com.sample.rest.service.*.*(..))")
    public void before() {
        System.out.println("before !!");
    }

    @Before("within(com.sample.rest.service.CustomerService) && args(id)")
    public void beforeArgs(Integer id) {
        System.out.println("before !! id=" + id);
    }

    @Before("within(com.sample.rest.service.CustomerService) && @annotation(com.sample.rest.common.TestAnnotation)")
    public void beforeAnnotation() {
        System.out.println("before !! test annotation");
    }

    @Around("within(com.sample.rest.service.CustomerService) && @annotation(com.sample.rest.common.TestAnnotation)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around - before !!");   // 前処理
        Object retVal = pjp.proceed();
        System.out.println("around - after !!");    // 後処理
        return retVal;
    }
}
