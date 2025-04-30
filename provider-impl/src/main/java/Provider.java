import com.ccc.Bootstrap;
import com.ccc.RpcProviderAPI;
import com.ccc.RpcProviderApiImpl;
import org.apache.catalina.LifecycleException;

public class Provider {
    public static void main(String[] args) throws LifecycleException {
        // 服务提供方在8080端口启动Tomcat服务器，并注册服务
        Bootstrap bootstrap = new Bootstrap("localhost", 8080);
        // 本地注册当前测试服务
        bootstrap.registerLocalService(RpcProviderAPI.class, RpcProviderApiImpl.class);
        bootstrap.startTomcat();

    }
}
