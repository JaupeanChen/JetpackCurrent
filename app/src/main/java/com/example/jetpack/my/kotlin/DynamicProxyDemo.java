package com.example.jetpack.my.kotlin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyDemo {

    public static void main(String[] args) {
        BaseDynamicImpl dynamicImpl = new BaseDynamicImpl();
        //通过委托类来构造调用处理器
        ProxyHandler proxyHandler = new ProxyHandler(dynamicImpl);
        //通过Java反射包的Proxy类来动态生成代理对象
        BaseDynamic proxy = (BaseDynamic) Proxy.newProxyInstance(
                BaseDynamicImpl.class.getClassLoader(),
                BaseDynamicImpl.class.getInterfaces(),
                proxyHandler);
        //通过代理对象调用方法
        proxy.doSomething();
    }
}

interface BaseDynamic {
    void doSomething();
}

//委托类
class BaseDynamicImpl implements BaseDynamic {

    @Override
    public void doSomething() {
        System.out.println("BaseDynamicImpl do something...");
    }
}

//代理类的调用处理器
class ProxyHandler implements InvocationHandler {
    //一样，我们需要持有共同的父类接口
    private final BaseDynamic baseDynamic;

    public ProxyHandler(BaseDynamic baseDynamic) {
        this.baseDynamic = baseDynamic;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return method.invoke(baseDynamic, objects);
    }
}
