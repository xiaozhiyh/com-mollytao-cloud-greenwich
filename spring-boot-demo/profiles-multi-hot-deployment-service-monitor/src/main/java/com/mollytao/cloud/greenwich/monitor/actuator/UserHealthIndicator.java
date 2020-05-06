package com.mollytao.cloud.greenwich.monitor.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 如果只是需要对应用的健康状态增加一些其他维度的数据，可以通过继承AbstractHealthIndicator来实现自己的业务逻辑
 * 是在框架自带的health端点中进行扩展
 * @author Administrator
 */
@Component
public class UserHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // up方法指定应用的状态为健康
        builder.up().withDetail("detailStatus", true);
        // down方法指定应用的状态不健康
        // withDetail方法用语添加一些详细信息。
//        builder.down().withDetail("detailStatus", false);
    }
}
