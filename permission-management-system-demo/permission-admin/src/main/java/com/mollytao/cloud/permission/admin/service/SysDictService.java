package com.mollytao.cloud.permission.admin.service;

import java.util.List;

import com.mollytao.cloud.permission.admin.model.SysDict;
import com.mollytao.cloud.permission.core.service.CurdService;

/**
 * 字典管理
 *
 * @author stephen
 * @date 2020-05-08
 */
public interface SysDictService extends CurdService<SysDict> {

    /**
     * 根据名称查询
     *
     * @param label
     * @return
     */
    List<SysDict> findByLabel(String label);
}
