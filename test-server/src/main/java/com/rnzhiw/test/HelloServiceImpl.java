package com.rnzhiw.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rnzhiw.rpc.annotation.Service;
import com.rnzhiw.rpc.api.HelloObject;
import com.rnzhiw.rpc.api.HelloService;

/**
 * @author rnzhiw
 */
@Service
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("接收到消息的id：{}，msg{}", object.getId(),object.getMessage());
        return "这是Impl1方法";
    }

}
