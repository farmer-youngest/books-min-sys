package com.marubi.security.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author tangmingze
 */
@Data
public class FormDemo1Dto implements Serializable {
    private String title;
    private String password;
    private Integer city;
    private Map<String,Object> like;
    private String switchtest;
    private String sex;
    private String desc;
}
