# ccc-rpc总结

### 1、rpc（远程方法调用）实现原理
+ 服务提供方需要进行服务注册，服务消费方需要进行服务发现，服务注册中心进行服务注册和发现；
+ 服务注册中心实现网络传输与动态代理；
+ 网络传输则需要序列化与反序列化，通常做法实现JDK的Serializable接口（阻塞IO），也可以使用Netty实现（非阻塞IO）；
+ 服务消费方通过动态代理服务实现调用远程方法和本地方法一样的效果；
+ 通过在服务注册中心加上负载均衡提高性能；
+ 传输协议：HTTP、TCP、SOCKET、UDP



### 2、ccc-rpc调用链路
+ rpc-framework目录实现了rpc的所有逻辑；
+ register目录LocalRegister提供了一个本地注册表，服务提供方通过接口名与对于的实现类进行注册；
+ protocol目录下的HttpServer启动了一个内嵌的TomCat服务器，负责处理网络请求；
+ 一般服务提供方在启动的时候，就将其提供的服务注册到本地注册表然后启动内嵌的TomCat服务器，这两部分逻辑封装进了Bootstrap启动类；
+ 服务消费方通过protocol目录下的ProxyFactory类实现的动态代理发送网络请求；
+ HttpClient通过HttpURLConnection发送HTTP请求至服务提供方启动的内嵌TomCat服务器；
+ TomCat上已经添加了一个我们自己实现的servlet（DispatchServlet），DispatchServlet调用了HttpServerHandler的handler方法用用于处理请求，最终通过请求体的内容通过接口名调用本地注册表获取服务实现类，然后调用服务实现类的方法并返回结果；
