package com.mollytao.cloud.permission.admin.service;

import java.util.List;

import com.mollytao.cloud.permission.admin.model.SysDept;
import com.mollytao.cloud.permission.core.service.CurdService;

/**
 * 机构管理
 *
 * @author stephen
 * @date 2020-05-08
 */
public interface SysDeptService extends CurdService<SysDept> {

    /**
     * 查询机构树
     *
     * @param userId
     * @return
     */
    List<SysDept> findTree();
}
