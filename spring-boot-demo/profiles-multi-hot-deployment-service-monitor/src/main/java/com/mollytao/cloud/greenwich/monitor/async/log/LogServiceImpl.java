package com.mollytao.cloud.greenwich.monitor.async.log;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Override
    @Async
    public void saveLog() {
        System.out.println(Thread.currentThread().getName());
    }

}
