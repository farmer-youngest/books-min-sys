package com.marubi.security.business.dto;

import com.marubi.security.common.dto.BasePageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookPageParamDto extends BasePageQuery implements Serializable {
    @ApiModelProperty("书名")
    private String title;
}
