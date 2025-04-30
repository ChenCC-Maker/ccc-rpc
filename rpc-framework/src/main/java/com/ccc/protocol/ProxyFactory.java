package com.ccc.protocol;

import com.ccc.common.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 实现动态代理，使服务使用方调用远程服务和调用本地服务没有什么区别
 * 服务调用端只需要知道服务实现类的类名，即可获取到对于类的代理，并且调用远程服务
 */
public class ProxyFactory {

    public static Object getProxy(Class interfaceClass) {
        // 创建代理对象，并且实现InvocationHandler接口
        Object proxy =  Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // Step1：封装调用发送网络请求参数
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                // Step2：发送网络请求至内嵌的Tomcat服务器，暂时写死localhost:8080
                HttpClient httpClient = new HttpClient();
                Object result = null;
                try {
                    result = httpClient.send("localhost", 8080, invocation);
                }catch (Exception e){
                    System.out.println("通过动态代理的方式，发送远程服务调用的网络请求出错，报错原因为："+e);
                }
                return result;
            }
        });

        return proxy;
    }
}
