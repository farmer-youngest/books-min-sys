package com.marubi.security.system.dto;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.marubi.security.system.MenuType;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户拥有的权限
 *
 * @author tmz
 */
@Data
public class AuthMapperUserDto implements Serializable {
    /**
     * 角色名称
     */
    private String authName;
    private String authIds;
    /**
     * 操作请求路径
     */
    private String menuIds;

    private String mapperUrl;
    /**
     * 操作名称
     */
    private String operationName;
    /**
     * url 的类型 {@link MenuType}
     */
    private Integer type;

    private Integer uid;
}
