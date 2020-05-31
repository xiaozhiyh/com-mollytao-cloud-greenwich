package com.mollytao.cloud.greenwich.monitor.async.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Override
    @Async
    public void saveLog() {
        // 记录trace级别的信息
        logger.trace("log4j2日志输出：This is trace message.");
        // 记录debug级别的信息
        logger.debug("log4j2日志输出：This is debug message.");
        // 记录info级别的信息
        logger.info("log4j2日志输出：This is info message.");
        // 记录error级别的信息
        logger.error("log4j2日志输出：This is error message.");
        System.out.println(Thread.currentThread().getName());
    }

}
