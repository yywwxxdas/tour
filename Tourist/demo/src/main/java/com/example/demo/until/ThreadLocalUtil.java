package com.example.demo.until;
//提供线程局部变量，使多个线程独立运行
public class ThreadLocalUtil {
    //提供ThreadLocal对象,
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();
    //根据键获得数据的值
    public static <T> T get()//定义泛型类T，接收各种数据
    {
        return (T) THREAD_LOCAL.get();
    }

    //根据键来存储数据的值
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }


    //清除存放的数据，防止内存泄漏
    public static void remove(){
        THREAD_LOCAL.remove();
    }

}
