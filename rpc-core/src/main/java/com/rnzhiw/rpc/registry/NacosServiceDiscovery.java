package com.rnzhiw.rpc.registry;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.rnzhiw.rpc.enumeration.RpcError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rnzhiw.rpc.exception.RpcException;
import com.rnzhiw.rpc.loadbalancer.LoadBalancer;
import com.rnzhiw.rpc.loadbalancer.RandomLoadBalancer;
import com.rnzhiw.rpc.util.NacosUtil;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Nacos服务发现
 *
 * @author rnzhiw
 */
public class NacosServiceDiscovery implements ServiceDiscovery {

    private static final Logger logger = LoggerFactory.getLogger(NacosServiceDiscovery.class);

    private final LoadBalancer loadBalancer;

    public NacosServiceDiscovery(LoadBalancer loadBalancer) {
        if(loadBalancer == null) {
            this.loadBalancer = new RandomLoadBalancer();
        } else {
            this.loadBalancer = loadBalancer;
        }
    }

    /**
     * 根据服务名称从Nacos中获取到所有提供这个服务的服务端信息列表并根据负载均衡策略选择一个服务端
     * @param serviceName 服务名称
     * @return
     */
    @Override
    public InetSocketAddress lookupService(String serviceName) {
        try {
            //从Nacos里获得服务实例
            List<Instance> instances = NacosUtil.getAllInstance(serviceName);
            if(instances.size() == 0) {
                logger.error("找不到对应的服务: " + serviceName);
                throw new RpcException(RpcError.SERVICE_NOT_FOUND);
            }
            Instance instance = loadBalancer.select(instances);
            return new InetSocketAddress(instance.getIp(), instance.getPort());
        } catch (NacosException e) {
            logger.error("获取服务时有错误发生:", e);
        }
        return null;
    }

}
