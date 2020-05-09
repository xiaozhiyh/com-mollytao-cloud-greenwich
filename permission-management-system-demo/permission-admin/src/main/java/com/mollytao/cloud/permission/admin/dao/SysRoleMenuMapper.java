package com.mollytao.cloud.permission.admin.dao;

import java.util.List;

import com.mollytao.cloud.permission.admin.model.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    List<SysRoleMenu> findRoleMenus(@Param(value = "roleId") Long roleId);

    List<SysRoleMenu> findAll();

    int deleteByRoleId(@Param(value = "roleId") Long roleId);
}