package com.leo.common.result;

import com.leo.common.result.CommonResult;
import lombok.Getter;

/**
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/20 15:17
 **/
@Getter
public enum EmBusinessResult implements CommonResult {
    /**
     * 通用状态
     */
    SUCCESS(200, "成功"),
    ERROR(400, "错误"),
    //1000开头定义为通用错误类型
    PARAMETER_VALIDATION_ERROR(100001,"参数不合法"),
    UNKNOWN_ERROR(100002, "未知错误"),
    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGINERROR(20002,"用户名或密码错误"),
    USER_REMOTEERROR(20003,"权限不足"),
    USER_REPERROR(20005,"重复操作"),
    USER_ACCOUNT_FREEZE(20006,"账号冻结"),
    PSSWORD_NOT_NULL(20007,"密码不能为空"),
    USER_EXIST(20008, "该用户名已经存在"),
    /**
     * 角色问题
     */
    ROLE_EXIST(20007, "该角色标识已经存在，不允许重复！"),

    /**
     * 部门问题
     */
    DEPT_EXIST_USER(20008, "部门存在用户，无法删除"),

    /**
     * 字典问题
     */
    DICT_EXIST(20009, "该字典标识已经存在，不允许重复！"),

    /**
     * 非法操作
     */
    STATUS_ERROR(20010, "非法操作：状态有误"),

    /**
     * 权限问题
     */
    NO_ADMIN_AUTH(50000, "不允许操作超级管理员"),
    NO_ADMIN_STATUS(50001, "不能修改超级管理员状态"),
    NO_ADMINROLE_AUTH(50002, "不允许操作管理员角色"),
    NO_ADMINROLE_STATUS(50003, "不能修改管理员角色状态"),
    ;
    private Integer code;

    private String message;

    EmBusinessResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}