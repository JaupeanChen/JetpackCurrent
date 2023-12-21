package com.example.jetpack.my.kotlin;

public class ProxyDemo {

    public static void main(String[] args) {
        BaseImp imp = new BaseImp();
        Proxy proxy = new Proxy(imp);
        proxy.doSomething();
    }
}

//interface Base {
//    void doSomething();
//}

//委托类
class BaseImp implements Base {

    @Override
    public void doSomething() {
        System.out.println("BaseImp do something...");
    }
}

//代理类
class Proxy implements Base {
    private final Base baseImp;

    public Proxy(Base base) {
        this.baseImp = base;
    }

    @Override
    public void doSomething() {
        baseImp.doSomething();
    }
}
