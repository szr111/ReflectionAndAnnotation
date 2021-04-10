package com.yc;

import com.yc.Biz.jsq;
import com.yc.Junit.*;

public class Mytest {

    private jsq j;

    @MyBeforeClass
    public static void BC(){
        System.out.println("beforeclass");
    }
    @MyBefore
    public void setup(){
        System.out.println("before");
        j=new jsq();

    }
    @MyAfter
    public void teardown(){
        System.out.println("after");
    }
    @MyAfterClass
    public static void ac(){
        System.out.println("AfterClass");
    }
    @MyTest
    public void add(){
        System.out.println("add测试");

    }
    @MyTest
    public void del(){
        System.out.println("del");

    }



}
