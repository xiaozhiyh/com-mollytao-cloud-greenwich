package com.mollytao.cloud.permission.admin.dao;

import java.util.List;

import com.mollytao.cloud.permission.admin.model.SysLog;
import org.apache.ibatis.annotations.Param;

public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
    
    List<SysLog> findPage();
    
    List<SysLog> findPageByUserName(@Param(value="userName") String userName);
}