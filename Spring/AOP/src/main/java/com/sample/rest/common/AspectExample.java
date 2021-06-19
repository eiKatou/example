package com.sample.rest.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
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

        // メソッドの引数の名前
        // 例. [id, name, age]
        String[] methodArgNames = ((CodeSignature) pjp.getSignature()).getParameterNames();

        // メソッドの引数の値
        // 例. ["001", "Alice", 18]
        Object[] methodArgValues = pjp.getArgs();

        // 「メソッドの引数の名前=メソッドの引数の値」形式で表示する
        // 例. id=001 name=Alice age=18
        for (int i = 0; i < methodArgNames.length; i++) {
            System.out.println(methodArgNames[i] + "=" + String.valueOf(methodArgValues[i]));
        }

        // 戻り値を取得
        Object retVal = pjp.proceed();

        System.out.println("around - after !!");    // 後処理

        // 戻り値を返す
        return retVal;
    }
}
