package com.ccc.protocol;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;


public class HttpServer {
    public void start(String hostName, int port) throws LifecycleException {
        // 获取Tomcat实例
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");
        // 处理网络请求，接收客户端请求
        Connector connector = new Connector();
        connector.setPort(port);
        // engine为Service下的顶级容器，管理多个host，负责分发请求
        StandardEngine standardEngine = new StandardEngine();
        // 指定engine下的默认host为hostName
        standardEngine.setDefaultHost(hostName);
        Host host = new StandardHost();
        host.setName(hostName);
        StandardContext standardContext = new StandardContext();
        // 设置上下文为根路径，确保可以通过根路径访问
        standardContext.setPath("");
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        // 将上下文、host按顺序添加到engine中
        host.addChild(standardContext);
        standardEngine.addChild(host);

        // service组合connector和container，协调请求处理
        service.setContainer(standardEngine);
        service.addConnector(connector);

        // 注册Servlet
        tomcat.addServlet("", "dispatchServlet", new DispatchServlet());
        standardContext.addServletMappingDecoded("/*", "dispatchServlet");

        // 启动Tomcat服务器
        tomcat.start();
        // 若不等待，主线程会在tomcat启动后立即退出，导致整个程序终止
        server.await();

    }
}