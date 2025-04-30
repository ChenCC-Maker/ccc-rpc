package com.ccc.register;


/**
 * 本地注册表，服务提供方应该在本地注册表进行注册，服务消费方根据服务名称从本地注册表中获取服务类
 * @author ccc
 * @date 2018/9/29
 */
public class LocalRegister {
    // 使用Map来存储注册信息
    private static final java.util.Map<String, Class> map = new java.util.HashMap<>();

    public static void register(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}
