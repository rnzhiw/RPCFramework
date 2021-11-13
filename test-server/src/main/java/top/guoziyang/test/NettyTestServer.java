package top.guoziyang.test;

import top.guoziyang.rpc.annotation.ServiceScan;
import top.guoziyang.rpc.serializer.CommonSerializer;
import top.guoziyang.rpc.transport.RpcServer;
import top.guoziyang.rpc.transport.netty.server.NettyServer;

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
