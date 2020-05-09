package com.mollytao.cloud.permission.admin.controller;

import java.util.List;

import com.mollytao.cloud.permission.admin.model.SysDict;
import com.mollytao.cloud.permission.admin.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mollytao.cloud.permission.core.http.HttpResult;
import com.mollytao.cloud.permission.core.page.PageRequest;

/**
 * 字典控制器
 * @author stephen
 * @date 2020-05-08
 */
@RestController
@RequestMapping("dict")
public class SysDictController {

	@Autowired
	private SysDictService sysDictService;
	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysDict record) {
		return HttpResult.ok(sysDictService.save(record));
	}

	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysDict> records) {
		return HttpResult.ok(sysDictService.delete(records));
	}

	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysDictService.findPage(pageRequest));
	}
	
	@GetMapping(value="/findByLabel")
	public HttpResult findByLabel(@RequestParam String label) {
		return HttpResult.ok(sysDictService.findByLabel(label));
	}
}
