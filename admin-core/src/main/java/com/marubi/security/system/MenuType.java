package com.marubi.security.system;

import lombok.AllArgsConstructor;
/**
 * 菜单表 类型
 * @author tmz
 * @date 2021/8/23
 */
@AllArgsConstructor
public enum MenuType {
    /**
     * 模块 0
     */
    MODULE
    /**
     * 菜单 1
     */
    ,MENU
    /**
     * 请求操作 2
     */
    ,REQUEST
    /**
     * 独立页面 3 ps: 登录页 404 、弹框
     */
    ,SEPARATE

    ;

}
