package com.yc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBConnection {

    /*@DBConnection(driver = "com.mysql.jdbc.Driver",url="jdbc:mysql://127.0.0.1:3306/test")*/
    public String driver() default "com.mysql.jdbc.Driver";
    public String url() default "jdbc:mysql://127.0.0.1:3306/test";
    public String user()default "root";
    public String password ()default "a";
}
