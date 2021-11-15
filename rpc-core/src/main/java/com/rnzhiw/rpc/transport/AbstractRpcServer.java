package com.rnzhiw.rpc.transport;

import com.rnzhiw.rpc.annotation.Service;
import com.rnzhiw.rpc.annotation.ServiceScan;
import com.rnzhiw.rpc.enumeration.RpcError;
import com.rnzhiw.rpc.provider.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rnzhiw.rpc.exception.RpcException;
import com.rnzhiw.rpc.registry.ServiceRegistry;
import com.rnzhiw.rpc.util.ReflectUtil;

import java.net.InetSocketAddress;
import java.util.Set;

/**
 * 实现了RpcServer接口，而 NettyServer 和 SocketServer 继承自 AbstractRpcServer
 * @author rnzhiw
 */
public abstract class AbstractRpcServer implements RpcServer {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String host;
    protected int port;

    protected ServiceRegistry serviceRegistry;
    protected ServiceProvider serviceProvider;

    /**
     * 扫描服务
     */
    public void scanServices() {
        //用于获取main所在的类
        String mainClassName = ReflectUtil.getStackTrace();
        Class<?> startClass;
        try {
            startClass = Class.forName(mainClassName);
            //通过 Class 对象的 isAnnotationPresent 方法来判断该类是否有 ServiceScan 注解
            if(!startClass.isAnnotationPresent(ServiceScan.class)) {
                logger.error("启动类缺少 @ServiceScan 注解");
                throw new RpcException(RpcError.SERVICE_SCAN_PACKAGE_NOT_FOUND);
            }
        } catch (ClassNotFoundException e) {
            logger.error("出现未知错误");
            throw new RpcException(RpcError.UNKNOWN_ERROR);
        }
        //如果有 ServiceScan 注解的话通过startClass.getAnnotation(ServiceScan.class).value()获取注解的值
        String basePackage = startClass.getAnnotation(ServiceScan.class).value();
        if("".equals(basePackage)) {
            //获得扫描范围
            basePackage = mainClassName.substring(0, mainClassName.lastIndexOf("."));
        }
        //当获得扫描的范围后，就可以通过ReflectUtil.getClasses(basePackage) 获取到所有的 Class 了
        Set<Class<?>> classSet = ReflectUtil.getClasses(basePackage);
        //逐个判断是否有 Service 注解，如果有的话，通过反射创建该对象，并且调用 publishService 注册即可
        for(Class<?> clazz : classSet) {
            if(clazz.isAnnotationPresent(Service.class)) {
                String serviceName = clazz.getAnnotation(Service.class).name();
                Object obj;
                try {
                    obj = clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    logger.error("创建 " + clazz + " 时有错误发生");
                    continue;
                }
                if("".equals(serviceName)) {
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> oneInterface: interfaces){
                        publishService(obj, oneInterface.getCanonicalName());
                    }
                } else {
                    //调用publishService注册
                    publishService(obj, serviceName);
                }
            }
        }
    }

    /**
     * 需要将服务保存在本地的注册表，同时注册到远程Nacos上。
     * @param service
     * @param serviceName
     * @param <T>
     */
    @Override
    public <T> void publishService(T service, String serviceName) {
        serviceProvider.addServiceProvider(service, serviceName);
        serviceRegistry.register(serviceName, new InetSocketAddress(host, port));
    }

}
