package com.rnzhiw.test;

import com.rnzhiw.rpc.api.ByeService;
import com.rnzhiw.rpc.api.HelloObject;
import com.rnzhiw.rpc.api.HelloService;
import com.rnzhiw.rpc.serializer.CommonSerializer;
import com.rnzhiw.rpc.transport.RpcClient;
import com.rnzhiw.rpc.transport.RpcClientProxy;
import com.rnzhiw.rpc.transport.netty.client.NettyClient;

/**
 * 测试用Netty消费者
 *
 * @author rnzhiw
 */
public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient(CommonSerializer.PROTOBUF_SERIALIZER);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
        ByeService byeService = rpcClientProxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Netty"));
    }

}
