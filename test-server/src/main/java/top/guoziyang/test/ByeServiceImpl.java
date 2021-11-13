package top.guoziyang.test;

import top.guoziyang.rpc.annotation.Service;
import top.guoziyang.rpc.api.ByeService;

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
