package com.marubi.security.core.api.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hhw
 */
@Data
public class ResultResp<T> implements Serializable {
    private String message;
    /**
     * code 200
     */
    private String code;

    private T data;
}
