package com.rnzhiw.rpc.registry;

import com.alibaba.nacos.api.exception.NacosException;
import com.rnzhiw.rpc.enumeration.RpcError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rnzhiw.rpc.exception.RpcException;
import com.rnzhiw.rpc.util.NacosUtil;

import java.net.InetSocketAddress;

/**
 * Nacos服务注册
 *
 * @author rnzhiw
 */
public class NacosServiceRegistry implements ServiceRegistry {

    private static final Logger logger = LoggerFactory.getLogger(NacosServiceRegistry.class);

    @Override
    public void register(String serviceName, InetSocketAddress inetSocketAddress) {
        try {
            NacosUtil.registerService(serviceName, inetSocketAddress);
        } catch (NacosException e) {
            logger.error("注册服务时有错误发生:", e);
            throw new RpcException(RpcError.REGISTER_SERVICE_FAILED);
        }
    }

}
