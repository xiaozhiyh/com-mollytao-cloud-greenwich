package com.mollytao.cloud.mybatisdemo.service;

import com.mollytao.cloud.mybatisdemo.model.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 查找所有用户
     *
     * @return
     */
    List<SysUser> findAll();

}