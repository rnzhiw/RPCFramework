package com.rnzhiw.rpc.provider;

/**
 * 保存和提供服务实例对象
 * 本地保存服务接口
 * @author rnzhiw
 */
public interface ServiceProvider {


    <T> void addServiceProvider(T service, String serviceName);

    Object getServiceProvider(String serviceName);

}
