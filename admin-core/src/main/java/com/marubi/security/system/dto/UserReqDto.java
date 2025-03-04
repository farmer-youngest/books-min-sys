package com.marubi.security.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限信息
 * @author tmz
 * @date 2021/8/30
 */
@Data
public class UserReqDto implements Serializable {
    private Integer authId;

    private String authName;

    private List<String> url;


}
