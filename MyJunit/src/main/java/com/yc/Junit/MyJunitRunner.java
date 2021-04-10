package com.yc.Junit;

import com.yc.Mytest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyJunitRunner {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class aClass = Class.forName("com.yc.Mytest");


        //获取这个类的所有方法
        Method[] methods = aClass.getDeclaredMethods();
        List<Method> list=new ArrayList<>();
        Method BeforeMethod=null;
        Method AfterMethod=null;
        Method BeforeClassMethod=null;
        Method AfterClassMethod=null;

        for (Method method : methods) {
            if(method.isAnnotationPresent(MyTest.class)){
                list.add(method);
            }
            if(method.isAnnotationPresent(MyBefore.class)){
                BeforeMethod=method;
            }
            if(method.isAnnotationPresent(MyBeforeClass.class)){
                BeforeClassMethod=method;
            }
            if(method.isAnnotationPresent(MyAfter.class)){
                AfterMethod=method;
            }
            if(method.isAnnotationPresent(MyAfterClass.class)){
                AfterClassMethod=method;
            }
        }

        if(list==null || list.size()<=0){
            throw new RuntimeException("没有要测试的方法");
        }
        Object o = aClass.newInstance();
        BeforeClassMethod.invoke(o,null);
        for (Method method : list) {
            if(BeforeMethod!=null){
                BeforeMethod.invoke(o,null);
            }
            method.invoke(o,null);
            if(AfterMethod!=null){
                AfterMethod.invoke(o,null);
            }
        }

        AfterClassMethod.invoke(o,null);
    }
}
