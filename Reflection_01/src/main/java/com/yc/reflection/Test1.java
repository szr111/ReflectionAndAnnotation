package com.yc.reflection;

import com.yc.showable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("请输入类路径");
            String path=sc.nextLine();
            System.out.println("待加载的类"+path);

            Class c = Class.forName(path);
            String name= c.getName();
            System.out.println(name);

            Field []fe=c.getDeclaredFields();
            System.out.println("这是该反射实例的属性---getdeclaredFileds");
            for (Field field : fe) {
                String modeifier="";
                switch (field.getModifiers()){
                    case 1:
                        modeifier="public";break;
                    case 2:
                        modeifier="private";break;

                }
                System.out.println(modeifier+"\t"+field.getName());
            }

            System.out.println("这是该反射实例的方法---getdeclaredMethods");
            Method[] methods = c.getDeclaredMethods();
            if(methods!=null && methods.length>0){
                for (Method method : methods) {

                    System.out.println(method.getModifiers()+"\t"+method.getReturnType()+"\t"+method.getName());
                }

            }

            System.out.println("这是该反射实例的构造方法---getdeclaredConstructors");
            final Constructor[] constructors = c.getDeclaredConstructors();
            if(constructors!=null && constructors.length>0){
                for (Constructor constructor : constructors) {
                    System.out.println(constructor.getModifiers()+"\t"+constructor.getName());
                }
            }

            System.out.println("利用反射完成实例化---newInstance");
            Object o = c.newInstance();
            if(o instanceof showable){
                showable p=(showable) o;
                p.show();
            }
            System.out.println("利用invoke() 调用激活该反射实例的方法");
            if(methods!=null && methods.length>0){
                for (Method method : methods) {
                    if(method.getName().startsWith("sh")){
                        method.invoke(o);
                    }
                }
            }

            System.out.println("利用反射来处理一个标准的javabean的对象");
            Map<String ,String > map=new HashMap<>();
            map.put("name","小龙");
            map.put("age","18");

            Object oo=getvale(map,c);
            System.out.println(oo.toString());


            /*Map<String ,String> pMap=new HashMap<>();
            pMap.put("name","张三");
            pMap.put("age",30+"");
            Object oo=*/
        }




    }

    /**
     * 反射 功能模块  将map中保存的 属性值存到  cls对应的对象中
     * @param map
     * @param cls
     * @return
     */
    public static Object getvale(Map<String ,String> map,Class cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o = cls.newInstance();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().startsWith("set")){
                //每一个set方法的名字
                String s = method.getName().substring(3).toLowerCase();
                String value=  map.get(s);
                if("Integer".equalsIgnoreCase(method.getParameterTypes()[0].getName())||"Int".equalsIgnoreCase(method.getParameterTypes()[0].getName())){
                    method.invoke(o,Integer.parseInt(value));
                }else{
                    method.invoke(o,value);
                }

            }
        }
        return o;
    }

}
