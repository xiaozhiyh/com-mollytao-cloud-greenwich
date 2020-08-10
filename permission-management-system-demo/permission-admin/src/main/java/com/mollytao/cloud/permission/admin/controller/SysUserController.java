package com.mollytao.cloud.permission.admin.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mollytao.cloud.permission.admin.model.SysUser;
import com.mollytao.cloud.permission.admin.service.SysUserService;
import com.mollytao.cloud.permission.admin.util.JwtTokenUtils;
import com.mollytao.cloud.permission.admin.util.PasswordUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mollytao.cloud.permission.admin.constant.SysConstants;
import com.mollytao.cloud.permission.common.utils.FileUtils;
import com.mollytao.cloud.permission.core.http.HttpResult;
import com.mollytao.cloud.permission.core.page.PageRequest;

/**
 * 用户控制器
 *
 * @author stephen
 * @date 2020-05-08
 */
@RestController
@RequestMapping("user")
@Api(value = "用户管理", tags = "用户管理2")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "保存用户", notes = "保存记录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 500, message = "数据备份失败")
    })
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody @ApiParam(name = "用户对象", value = "传入json格式", required = true) SysUser record) {
        SysUser user = sysUserService.findById(record.getId());
        if (user != null) {
            if (SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
                return HttpResult.error("超级管理员不允许修改!");
            }
        }
        if (record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if (user == null) {
                // 新增用户
                if (sysUserService.findByName(record.getName()) != null) {
                    return HttpResult.error("用户名已存在!");
                }
                String password = PasswordUtils.encode(record.getPassword(), salt);
                record.setSalt(salt);
                record.setPassword(password);
            } else {
                // 修改用户, 且修改了密码
                if (!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encode(record.getPassword(), salt);
                    record.setSalt(salt);
                    record.setPassword(password);
                }
            }
        }
        return HttpResult.ok(sysUserService.save(record));
    }

    @ApiOperation(value = "删除用户", notes = "删除记录")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody @ApiParam(name = "用户对象数组", value = "传入json数组格式", required = true) List<SysUser> records) {
        for (SysUser record : records) {
            SysUser sysUser = sysUserService.findById(record.getId());
            if (sysUser != null && SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) {
                return HttpResult.error("超级管理员不允许删除!");
            }
        }
        return HttpResult.ok(sysUserService.delete(records));
    }

    @ApiOperation(value = "根据名称查询", notes = "根据名称查询")
    @GetMapping(value = "/findByName")
    public HttpResult findByName(@RequestParam @ApiParam(name = "用户名称", value = "name", required = true) String name) {
        return HttpResult.ok(sysUserService.findByName(name));
    }

    @ApiOperation(value = "查询用户权限", notes = "查询用户权限")
    @GetMapping(value = "/findPermissions")
    public HttpResult findPermissions(@RequestParam @ApiParam(name = "用户名称", value = "name", required = true)  String name) {
        return HttpResult.ok(sysUserService.findPermissions(name));
    }

    @ApiOperation(value = "查询用户角色", notes = "查询用户角色")
    @GetMapping(value = "/findUserRoles")
    public HttpResult findUserRoles(@RequestParam @ApiParam(name = "用户id", value = "name", required = true) Long userId) {
        return HttpResult.ok(sysUserService.findUserRoles(userId));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody @ApiParam(name = "分页查询请求", value = "name", required = true) PageRequest pageRequest, HttpServletRequest request) {
        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);
        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }

    @ApiOperation(value = "删除用户", notes = "删除记录")
    @PostMapping(value = "/exportExcelUser")
    public void exportExcelUser(@RequestBody @ApiParam(name = "导出数据请求", value = "name", required = true) PageRequest pageRequest, HttpServletResponse res) {
        File file = sysUserService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(res, file, file.getName());
    }

}
