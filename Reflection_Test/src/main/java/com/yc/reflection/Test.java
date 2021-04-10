package com.yc.reflection;

import com.yc.Bean.user;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        System.out.println("获取反射类");
        Class aClass = user.class;

        //获取该反射类属性
        System.out.println("这是该反射类的属性===");
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            String modeif="";
            switch (field.getModifiers()){
                case 1:
                modeif="public";
                break;
                case 2:
                    modeif="private";
                    break;
            }
            System.out.println(modeif+"\t"+field.getType()+"\t"+field.getName());
        }
        //获取该反射类的构造方法
        System.out.println("这是该反射类的构造方法===");
        Constructor[] constructors = aClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getModifiers()+"\t"+constructor.getName());
        }

        System.out.println("这是该反射类自己定义的方法===");
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getModifiers()+"\t"+method.getReturnType()+"\t"+method.getName());
        }

        //开始修改该反射
        //第一步创建一个反射对象
        System.out.println("修改反射实例的属性或者方法");
        Object o = aClass.newInstance();
        if(o instanceof user){
            System.out.println("该反射的实例为user类的实例");
        }

        //激活该实例中的show方法
        if(methods!=null && methods.length>0){
            for (Method method : methods) {
                if(method.getName().startsWith("sh")){
                    method.invoke(o);
                }
            }
        }
        //调用setvalue对该反射实例进行设值

        HashMap<String, String> map = new HashMap<>();
        map.put("name","xiaoli");
        map.put("age","12");
        Object o1=setValue(map,aClass);
        System.out.println(o1.toString());

    }
    public static Object setValue(Map<String,String> map ,Class cls) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Object o = cls.newInstance();
        Method[] methods = cls.getDeclaredMethods();
        //先找到set方法 然后注入
        for (Method method : methods) {
            if(method.getName().startsWith("set")){
                String key=method.getName().substring(3).toLowerCase();
                String value = map.get(key);
                if("Integer".equalsIgnoreCase(method.getParameterTypes()[0].getName())||
                        "Int".equalsIgnoreCase(method.getParameterTypes()[0].getName())){
                    method.invoke(o,Integer.parseInt(value));
                }else{
                    method.invoke(o,value);
                }

            }
        }

        return o;
    }
}
