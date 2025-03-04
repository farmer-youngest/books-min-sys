package com.marubi.security.system.dto;

import com.marubi.security.common.dto.BasePageQuery;
import lombok.Data;

@Data
public class AdminParamDto extends BasePageQuery {
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态，0:禁用、1:启用
     */
    private Integer status;


}
