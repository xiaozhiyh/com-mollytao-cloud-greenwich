package com.mollytao.cloud.greenwich.monitor.controller;

import com.mollytao.cloud.greenwich.monitor.async.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("asyncLog")
    public String asyncPrintLog() {
        logService.saveLog();
        System.out.println("---------" + Thread.currentThread().getName());
        return "ccc";
    }
}
