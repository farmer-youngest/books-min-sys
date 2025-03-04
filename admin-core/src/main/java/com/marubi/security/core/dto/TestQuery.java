package com.marubi.security.core.dto;

import com.marubi.security.common.dto.BasePageQuery;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestQuery extends BasePageQuery {
    private Integer id;

    private String name;

    private Integer version;
    private LocalDateTime createTime;

}
