package com.mollytao.cloud.permission.data.backup.controller;

import com.mollytao.cloud.permission.common.utils.FileUtils;
import com.mollytao.cloud.permission.data.backup.constants.BackupConstants;
import com.mollytao.cloud.permission.data.backup.datasource.BackupDataSourceProperties;
import com.mollytao.cloud.permission.data.backup.service.MysqlBackupService;
import com.mollytao.cloud.permission.data.backup.utils.HttpResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统数据备份还原
 *
 * @author Louis
 * @date Jan 15, 2019
 */
@RestController
@RequestMapping("/backup")
@Api(value = "备份还原controller", tags = "备份还原控制器")
/**
 * @Api：用在请求的类上，表示对类的说明 tags="说明该类的作用，可以在UI界面上看到的注解" value="该参数没什么意义，在UI界面上也看到，所以不需要配置"
 */
public class MySqlBackupController {

    @Autowired
    MysqlBackupService mysqlBackupService;
    @Autowired
    BackupDataSourceProperties properties;

    /**
     * @ApiOperation：用在请求的方法上，说明方法的用途、作用 value="说明方法的用途、作用" notes="方法的备注说明"
     * @ApiImplicitParams：用在请求的方法上，表示一组参数说明
     * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面 name：参数名
     * value：参数的汉字说明、解释
     * required：参数是否必须传
     * paramType：参数放在哪个地方
     * · header --> 请求参数的获取：@RequestHeader
     * · query --> 请求参数的获取：@RequestParam
     * · path（用于restful接口）--> 请求参数的获取：@PathVariable
     * · body（不常用）
     * · form（不常用）
     * dataType：参数类型，默认String，其它值dataType="Integer"
     * defaultValue：参数的默认值
     * @ApiResponses：用在请求的方法上，表示一组响应
     * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息 code：数字，例如400
     * message：信息，例如"请求参数没填好"
     * response：抛出异常的类
     */
    @ApiOperation(value = "数据备份接口", notes = "读取数据源信息及备份信息生成数据备份")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 500, message = "数据备份失败")
    })
    @GetMapping("/backup")
    public HttpResult backup() {
        String backupFolderName = BackupConstants.DEFAULT_BACKUP_NAME + "_" + (new SimpleDateFormat(BackupConstants.DATE_FORMAT)).format(new Date());
        return backup(backupFolderName);
    }

    private HttpResult backup(String backupFodlerName) {
        String host = properties.getHost();
        String userName = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String backupFolderPath = BackupConstants.BACKUP_FOLDER + backupFodlerName + File.separator;
        String fileName = BackupConstants.BACKUP_FILE_NAME;
        try {
            boolean success = mysqlBackupService.backup(host, userName, password, backupFolderPath, fileName, database);
            if (!success) {
                HttpResult.error("数据备份失败");
            }
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok();
    }

    @ApiOperation(value = "数据还原接口", notes = "读取数据源信息和还原版本进行数据还原")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "备份的数据版本名称", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 500, message = "数据还原失败")
    })
    @GetMapping("/restore")
    public HttpResult restore(@RequestParam String name) throws IOException {
        String host = properties.getHost();
        String userName = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String restoreFilePath = BackupConstants.RESTORE_FOLDER + name;
        try {
            mysqlBackupService.restore(restoreFilePath, host, userName, password, database);
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok();
    }

    @ApiOperation(value = "备份查找接口", notes = "用于查找和向用户展示数据备份版本")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功")
    })
    @GetMapping("/findRecords")
    public HttpResult findBackupRecords() {
        if (!new File(BackupConstants.DEFAULT_RESTORE_FILE).exists()) {
            // 初始默认备份文件
            backup(BackupConstants.DEFAULT_BACKUP_NAME);
        }
        List<Map<String, String>> backupRecords = new ArrayList<>();
        File restoreFolderFile = new File(BackupConstants.RESTORE_FOLDER);
        if (restoreFolderFile.exists()) {
            for (File file : restoreFolderFile.listFiles()) {
                Map<String, String> backup = new HashMap<>();
                backup.put("name", file.getName());
                backup.put("title", file.getName());
                if (BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(file.getName())) {
                    backup.put("title", "系统默认备份");
                }
                backupRecords.add(backup);
            }
        }
        // 排序，默认备份最前，然后按时间戳排序，新备份在前面
        backupRecords.sort((o1, o2) -> BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o1.get("name")) ? -1
                : BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o2.get("name")) ? 1 : o2.get("name").compareTo(o1.get("name")));
        return HttpResult.ok(backupRecords);
    }

	@ApiOperation(value = "备份删除接口", notes = "通过备份还原管理界面删除数据备份版本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "备份的数据版本名称", required = true, paramType = "query")
    })
	@ApiResponses({
			@ApiResponse(code = 200, message = "请求成功"),
			@ApiResponse(code = 500, message = "删除失败")
	})
    @GetMapping("/delete")
    public HttpResult deleteBackupRecord(@RequestParam String name) {
        if (BackupConstants.DEFAULT_BACKUP_NAME.equals(name)) {
            return HttpResult.error("系统默认备份无法删除!");
        }
        String restoreFilePath = BackupConstants.BACKUP_FOLDER + name;
        try {
            FileUtils.deleteFile(new File(restoreFilePath));
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok();
    }

}
