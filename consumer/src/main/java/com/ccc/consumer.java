package com.ccc;

import com.ccc.protocol.ProxyFactory;

public class consumer {
    public static void main(String[] args) {
        RpcProviderAPI rpcProviderAPI = (RpcProviderAPI) ProxyFactory.getProxy(RpcProviderAPI.class);
        System.out.println(rpcProviderAPI.sayHello("chencc"));
    }
}
