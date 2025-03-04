package com.marubi.security.system.dto;

import com.marubi.security.common.dto.BasePageQuery;
import lombok.Data;

@Data
public class AuthParamDto extends BasePageQuery {
    private Integer id;
    /**
     * 组名
     */
    private String title;
    /**
     * 状态，0:禁用、1:启用
     */
    private Integer status;
}
