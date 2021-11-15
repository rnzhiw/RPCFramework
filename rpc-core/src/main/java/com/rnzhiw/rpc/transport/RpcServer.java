package com.rnzhiw.rpc.transport;

import com.rnzhiw.rpc.serializer.CommonSerializer;

/**
 * 服务器类通用接口
 *
 * @author rnzhiw
 */
public interface RpcServer {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    void start();

    <T> void publishService(T service, String serviceName);

}
