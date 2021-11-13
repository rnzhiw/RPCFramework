package top.guoziyang.rpc.registry;

import java.net.InetSocketAddress;

/**
 * 服务发现接口
 * 作为远程注册表（Nacos）使用
 * @author rnzhiw
 */
public interface ServiceDiscovery {

    /**
     * 根据名称从注册中心获取到一个服务提供者的地址
     *
     * @param serviceName 服务名称
     * @return 服务实体
     */
    InetSocketAddress lookupService(String serviceName);

}
