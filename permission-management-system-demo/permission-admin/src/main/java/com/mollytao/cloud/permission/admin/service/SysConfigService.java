package com.mollytao.cloud.permission.admin.service;

import java.util.List;

import com.mollytao.cloud.permission.admin.model.SysConfig;
import com.mollytao.cloud.permission.core.service.CurdService;

/**
 * 系统配置管理
 * @author stephen
 * @date 2020-05-08
 */
public interface SysConfigService extends CurdService<SysConfig> {

	/**
	 * 根据名称查询
	 * @param label
	 * @return
	 */
	List<SysConfig> findByLabel(String label);
}
