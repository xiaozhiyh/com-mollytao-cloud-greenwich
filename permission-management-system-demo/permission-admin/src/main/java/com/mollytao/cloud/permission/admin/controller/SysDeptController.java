package com.mollytao.cloud.permission.admin.controller;

import java.util.List;

import com.mollytao.cloud.permission.admin.model.SysDept;
import com.mollytao.cloud.permission.admin.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mollytao.cloud.permission.core.http.HttpResult;

/**
 * 机构控制器
 *
 * @author stephen
 * @date 2020-05-08
 */
@RestController
@RequestMapping("dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysDept record) {
        return HttpResult.ok(sysDeptService.save(record));
    }

    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysDept> records) {
        return HttpResult.ok(sysDeptService.delete(records));
    }

    @GetMapping(value = "/findTree")
    public HttpResult findTree() {
        return HttpResult.ok(sysDeptService.findTree());
    }

}
