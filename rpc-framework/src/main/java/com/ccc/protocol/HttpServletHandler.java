package com.ccc.protocol;

import com.ccc.common.Invocation;
import com.ccc.register.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

public class HttpServletHandler {
    /** 接收客户端请求，处理请求返回具体的相应内容
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Step1：解析request对象，获取请求体内容
        Invocation invocation;
        invocation = (Invocation) new ObjectInputStream(request.getInputStream()).readObject();

        String interFaceName = invocation.getInterFaceName();
        String methodName = invocation.getMethodName();
        Class[] parameterTypes = invocation.getParameterTypes();
        Object[] parameterValues =  invocation.getParameterValues();

        // Step2：拿到接口名，通过本地注册表拿到对应的实现类
        Class implClass = LocalRegister.get(interFaceName);
        // Step3：拿到实现类，调用对应的方法
        Method method = implClass.getMethod(methodName, parameterTypes);
        Object invokeResult = method.invoke(implClass.newInstance(), parameterValues);

        // Step4：将结果返回给客户端
        response.getOutputStream().write(invokeResult.toString().getBytes());
    }
}
