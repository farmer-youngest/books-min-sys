package com.marubi.security.common.codes;

/**
 * 错误码定义：
 * <p>
 * 1.前端导致的错误码由A+4位数字组成
 * 2.自身业务系统导致的错误码由B+4位数字组成
 * 3.第三方系统导致的错误码由C+4位数字组成
 *
 * @Date 2020-10-27
 * @Description
 * @Version 1.0
 */
public enum ErrorCode {

    /**
     * 前端未知错误
     **/
    A0001("前端未知错误"),
    /**
     * 系统未知错误
     **/
    B0001("系统未知错误"),
    /**
     * 第三方系统未知错误
     **/
    C0001("第三方系统未知错误"),

    /**
     * 非法的请求参数
     **/
    A0002("非法的请求参数:%s"),
    /**
     * 非法操作
     **/
    A0003("非法操作:%s"),
    /**
     * 权限受限
     **/
    A0004("权限受限:%s"),
    /**
     * 验证码错误
     **/
    A0005("验证码错误"),
    /**
     * 账号与密码不匹配
     **/
    A0006("账号与密码不匹配"),
    /**
     * oauth token invalid
     **/
    A0008("oauth token invalid"),
    /**
     * 唯一键冲突
     **/
    A0009("唯一键冲突，重复插入"),
    /**
     * 字段非法
     **/
    A0010("插入数据库失败，字段非法"),
    /**
     * 提交的数据包超过上限
     **/
    A0011("请求提交的数据包超过上限:%d"),
    /**
     * 数据库操作异常
     */
    A0012("数据库操作异常"),

    /**
     * 数据库访问异常
     **/
    B0002("数据库访问异常"),
    /**
     * 网络访问异常
     **/
    B0003("网络访问异常"),

    /**
     * 密码编码错误
     */
    B0005("用户名或者密码错误"),
    B0006("字段有效校验错误"),

    A0101("当前登录用户存在重复用户，请来信管理员解决问题"),

    A0102("用户不存在"),

    A0201("更新失败"),

    A0202("用户名重复，请重新输入"),

    A0203("管理员组名重复，请重新输入"),

    A0204("该管理员组仍存在用户，不可删除组"),

    A0301("该品牌存在关联产品，不可删除"),

    C0101("文件路径为空"),

    C0102("查无此配置，请来信管理员解决问题"),

    C0103("存在重复配置项，请来信管理员解决问题"),
    C0110("当前uid:[%s]没有对应的角色请联系管理员"),

    D0101("产品编码已存在，请确认后重新操作"),

    M0101("菜单唯一标识重复，请确认后重新操作"),

    M0102("此项已锁定不可删！"),

    M0103("此项仍存在子节点，不可删除！"),

    P0101("当前防窜码code没有对应产品详情：%s"),

    P0102("当前防窜码为空")
            ;

    private final String msg;

    ErrorCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
