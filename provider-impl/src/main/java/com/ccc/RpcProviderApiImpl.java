package com.ccc;

public class RpcProviderApiImpl implements RpcProviderAPI{
    @Override
    public String sayHello(String name) {
        System.out.println("hello ccc-rpc,"+name);
        return "hello ccc-rpc,"+name;
    }
}
