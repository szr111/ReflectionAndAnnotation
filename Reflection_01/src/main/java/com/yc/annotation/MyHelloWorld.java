package com.yc.annotation;

//声名它的特征   Target :

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyHelloWorld {

    public String name();

    public int age() default 20;

    public String[] ins();

}
