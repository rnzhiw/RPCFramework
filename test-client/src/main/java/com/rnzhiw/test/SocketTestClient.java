package com.rnzhiw.test;

import lombok.extern.slf4j.Slf4j;
import com.rnzhiw.rpc.api.ByeService;
import com.rnzhiw.rpc.api.HelloObject;
import com.rnzhiw.rpc.api.HelloService;
import com.rnzhiw.rpc.serializer.CommonSerializer;
import com.rnzhiw.rpc.transport.RpcClientProxy;
import com.rnzhiw.rpc.transport.socket.client.SocketClient;

/**
 * 测试用消费者（客户端）
 *
 * @author rnzhiw
 */
@Slf4j
public class SocketTestClient {

    public static void main(String[] args) {
        SocketClient client = new SocketClient(CommonSerializer.DEFAULT_SERIALIZER);
        RpcClientProxy proxy = new RpcClientProxy(client);
        //第一个消费者连接
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
        //第二个消费者连接
        ByeService byeService = proxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Netty"));
    }

}
