package com.mollytao.cloud.permission.admin.controller;

import java.util.List;

import com.mollytao.cloud.permission.admin.model.SysConfig;
import com.mollytao.cloud.permission.admin.service.SysConfigService;
import com.mollytao.cloud.permission.core.http.HttpResult;
import com.mollytao.cloud.permission.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统配置控制器
 * @author stephen
 * @date 2020-05-08
 */
@RestController
@RequestMapping("config")
public class SysConfigController {

	@Autowired
	private SysConfigService sysConfigService;
	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysConfig record) {
		return HttpResult.ok(sysConfigService.save(record));
	}

	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysConfig> records) {
		return HttpResult.ok(sysConfigService.delete(records));
	}

	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysConfigService.findPage(pageRequest));
	}
	
	@GetMapping(value="/findByLabel")
	public HttpResult findByLabel(@RequestParam String label) {
		return HttpResult.ok(sysConfigService.findByLabel(label));
	}
}
