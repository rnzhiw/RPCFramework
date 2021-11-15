package com.rnzhiw.test;

import com.rnzhiw.rpc.annotation.ServiceScan;
import com.rnzhiw.rpc.serializer.CommonSerializer;
import com.rnzhiw.rpc.transport.RpcServer;
import com.rnzhiw.rpc.transport.socket.server.SocketServer;

/**
 * 测试用服务提供方（服务端）
 *
 * @author rnzhiw
 */
@ServiceScan
public class SocketTestServer {

    public static void main(String[] args) {
        RpcServer server = new SocketServer("127.0.0.1", 9998, CommonSerializer.HESSIAN_SERIALIZER);
        server.start();
    }

}
