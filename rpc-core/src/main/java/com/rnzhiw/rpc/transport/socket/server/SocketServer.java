package com.rnzhiw.rpc.transport.socket.server;

import com.rnzhiw.rpc.provider.ServiceProviderImpl;
import com.rnzhiw.rpc.transport.AbstractRpcServer;
import com.rnzhiw.rpc.factory.ThreadPoolFactory;
import com.rnzhiw.rpc.handler.RequestHandler;
import com.rnzhiw.rpc.hook.ShutdownHook;
import com.rnzhiw.rpc.registry.NacosServiceRegistry;
import com.rnzhiw.rpc.serializer.CommonSerializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * Socket方式远程方法调用的提供者（服务端）
 *
 * @author rnzhiw
 */
public class SocketServer extends AbstractRpcServer {

    private final ExecutorService threadPool;
    private final CommonSerializer serializer;
    private final RequestHandler requestHandler = new RequestHandler();

    public SocketServer(String host, int port) {
        this(host, port, DEFAULT_SERIALIZER);
    }

    public SocketServer(String host, int port, Integer serializer) {
        this.host = host;
        this.port = port;
        threadPool = ThreadPoolFactory.createDefaultThreadPool("socket-rpc-server");
        this.serviceRegistry = new NacosServiceRegistry();
        this.serviceProvider = new ServiceProviderImpl();
        this.serializer = CommonSerializer.getByCode(serializer);
        //扫描服务
        scanServices();
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress(host, port));
//            logger.info("服务器启动……");
            System.out.println("服务器启动......");
            //调用钩子函数，关闭后将注销所有服务
            ShutdownHook.getShutdownHook().addClearAllHook();
            //开启socket等待客户端（消费者）连接
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                logger.info("消费者连接: {}:{}", socket.getInetAddress(), socket.getPort());
                threadPool.execute(new SocketRequestHandlerThread(socket, requestHandler, serializer));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            logger.error("服务器启动时有错误发生:", e);
        }
    }

}
