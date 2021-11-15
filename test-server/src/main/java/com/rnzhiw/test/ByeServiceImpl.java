package com.rnzhiw.test;

import com.rnzhiw.rpc.annotation.Service;
import com.rnzhiw.rpc.api.ByeService;

/**
 * @author rnzhiw
 */
@Service
public class ByeServiceImpl implements ByeService {

    @Override
    public String bye(String name) {
        if(name == null) {
            return null;
        }
        return "bye, " + name;
    }
}
