package com.mollytao.cloud.permission.admin.dao;

import java.util.List;

import com.mollytao.cloud.permission.admin.model.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    List<SysUser> findPage();
    
    SysUser findByName(@Param(value="name") String name);
    
	List<SysUser> findPageByName(@Param(value="name") String name);
	
	List<SysUser> findPageByNameAndEmail(@Param(value="name") String name, @Param(value="email") String email);
}