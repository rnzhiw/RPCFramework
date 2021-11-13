package top.guoziyang.rpc.registry;

import java.net.InetSocketAddress;

/**
 * 服务注册接口
 * 作为远程注册表（Nacos）使用
 * @author rnzhiw
 */
public interface ServiceRegistry {

    /**
     * 将服务的名称和地址注册进服务注册中心
     *
     * @param serviceName 服务名称
     * @param inetSocketAddress 提供服务的地址
     */
    void register(String serviceName, InetSocketAddress inetSocketAddress);


}
