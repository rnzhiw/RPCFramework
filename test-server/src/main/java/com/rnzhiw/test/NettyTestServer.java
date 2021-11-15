package com.rnzhiw.test;

import com.rnzhiw.rpc.annotation.ServiceScan;
import com.rnzhiw.rpc.serializer.CommonSerializer;
import com.rnzhiw.rpc.transport.RpcServer;
import com.rnzhiw.rpc.transport.netty.server.NettyServer;

/**
 * 测试用Netty服务提供者（服务端）
 * 直接使用启动类所在的包作为扫描根包
 *
 * @author rnzhiw
 */
@ServiceScan
public class NettyTestServer {

    public static void main(String[] args) {
        RpcServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.PROTOBUF_SERIALIZER);
        server.start();
    }

}
