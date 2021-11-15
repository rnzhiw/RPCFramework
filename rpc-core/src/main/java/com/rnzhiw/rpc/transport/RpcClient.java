package com.rnzhiw.rpc.transport;

import com.rnzhiw.rpc.entity.RpcRequest;
import com.rnzhiw.rpc.serializer.CommonSerializer;

/**
 * 客户端类通用接口
 *
 * @author rnzhiw
 */
public interface RpcClient {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    Object sendRequest(RpcRequest rpcRequest);

}
