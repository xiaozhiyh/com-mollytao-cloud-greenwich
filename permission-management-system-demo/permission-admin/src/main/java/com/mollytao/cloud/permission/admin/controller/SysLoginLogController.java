package com.mollytao.cloud.permission.admin.controller;

import java.util.List;

import com.mollytao.cloud.permission.admin.model.SysLoginLog;
import com.mollytao.cloud.permission.admin.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mollytao.cloud.permission.core.http.HttpResult;
import com.mollytao.cloud.permission.core.page.PageRequest;

/**
 * 登录日志控制器
 * @author stephen
 * @date 2020-05-08
 */
@RestController
@RequestMapping("loginlog")
public class SysLoginLogController {

	@Autowired
	private SysLoginLogService sysLoginLogService;

	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysLoginLogService.findPage(pageRequest));
	}
	
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysLoginLog> records) {
		return HttpResult.ok(sysLoginLogService.delete(records));
	}
}
