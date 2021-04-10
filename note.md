##反射：
```xml
JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法
        对于任意一个对象，都能够调用它的任意方法和属性；这种动态获取信息以及动态调用对象方法的功能称为java语言的反射机制。

```
###获取类的方法
    1. Class c = Class.forName(path);通过类路径找到
    2. Class aClass = Conncetion.class;类名.class
    
###1.获取通过反射得到类的属性
```xmlgetDeclaredFields```  **getModifiers()修饰符 **
###2.获取该类的方法
`getDeclaredMethods`
###3.获取该类的构造方法
`getDeclaredConstructors`
###4.获取该类的构造方法
`getDeclaredConstructors`
###5.利用反射完成加载该类的实例化
`newinstance`
###6.利用invoke() 调用激活该反射实例的方法
`method.invoke(实例o,要激活的方法参数 可为null)`
##7.利用反射来处理一个标准的javabean的对象
```xml 
Map<String ,String > map=new HashMap<>();
            map.put("name","小龙");
            map.put("age","18");

            Object oo=getvale(map,c);
            System.out.println(oo.toString());

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
```

### 7.注解
####元注解
```xml
@target({ElementType.TYPE,ElementType.FIELD});注解允许在的范围

@rentention(RetentionPolicy.RUNTIME) 注解在什么时候存在
@documented
@Inherited 是否要继承父类的注解
```
