package com.ccc;

import com.ccc.protocol.HttpServer;
import com.ccc.register.LocalRegister;
import org.apache.catalina.LifecycleException;

/**
 * rpc-framework启动
 */
public class Bootstrap {
    String hostName;
    int port;

    public Bootstrap(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    // 启动Tomcat服务器
    public void startTomcat() throws LifecycleException {
        HttpServer httpServer = new HttpServer();
        httpServer.start(hostName, port);
        System.out.println("tomcat服务以启动在"+hostName+":"+port);
    }

    // 注册本地服务
    public <T>void registerLocalService(Class<T> interFace, Class implClass) {
        LocalRegister.register(interFace.getName(), implClass);
        System.out.println("本地服务已注册："+interFace.getName());
    }

}
