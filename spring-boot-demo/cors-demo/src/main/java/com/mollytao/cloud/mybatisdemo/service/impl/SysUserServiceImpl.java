package com.mollytao.cloud.mybatisdemo.service.impl;

import com.mollytao.cloud.mybatisdemo.dao.SysUserMapper;
import com.mollytao.cloud.mybatisdemo.model.SysUser;
import com.mollytao.cloud.mybatisdemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }
}