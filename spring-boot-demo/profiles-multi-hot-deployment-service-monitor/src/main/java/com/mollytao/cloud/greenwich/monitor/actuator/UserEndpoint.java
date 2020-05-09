package com.mollytao.cloud.greenwich.monitor.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Component
@Endpoint(id = "user")
public class UserEndpoint {

    /**
     * 必须加此注解，否则将读取不到自定义的actuator节点
     *
     * @return
     */
    @ReadOperation
    public List<Map<String, Object>> health() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1001);
        map.put("userName", "mollytao");
        list.add(map);
        return list;
    }
}
