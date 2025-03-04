package com.marubi.security.common.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import com.marubi.security.common.codes.ErrorCode;
import com.marubi.security.common.exceptions.BaseBusinessException;

import java.io.Serializable;

/**
 * @Date 2020-10-27
 * @Description
 * @Version 1.0
 */
@Data
@Setter(AccessLevel.NONE)
public class Result<T> implements Serializable {

    public final static String SUCCESS_CODE="200";
    public final static Result SUCCESS = Result.build(SUCCESS_CODE);

    public final static Result FRONTEND_ERR = Result.build(ErrorCode.A0001);
    public final static Result SYSTEM_ERR = Result.build(ErrorCode.B0001);
    public final static Result THIRD_PARTY_ERR = Result.build(ErrorCode.C0001);

    public static <T> Result<T> build(String code){
        return new Result<T>(code,null,null);
    }

    public static <T> Result<T> build(String code,String info){
        return new Result<T>(code,info,null);
    }

    public static <T> Result<T> build(String code,String info,T data){
        return new Result<T>(code,info,data);
    }

    public static <T> Result<T> build(String code,T data){
        return new Result<T>(code,null,data);
    }

    public static <T> Result<T> build(T data){
        return new Result<T>(data);
    }

    public static <T> Result<T> build(ErrorCode code){
        return new Result<T>(code.name(),code.getMsg());
    }

    public static <T> Result<T> build(ErrorCode code,String info){
        return new Result<T>(code.name(),info);
    }

    public static <T> Result<T> build(ErrorCode code,String info, T data){
        return new Result<T>(code.name(),info,data);
    }

    public static <T> Result<T> build(BaseBusinessException e){
        return new Result<T>(e.getErrorCode(),e.getErroMessage());
    }

//    @Schema(description = "错误码：'00000'代表成功，其它代表失败")
    private final String code;

//    @Schema(description = "错误信息")
    private final String info;

//    @Schema(description = "data")
    private final T data;


    public Result(T data) {
        this(SUCCESS_CODE,null,data);
    }

    public Result(String code) {
        this(code,null,null);
    }

    public Result(String code, String info) {
        this(code,info,null);
    }

    @JsonCreator
    public Result(@JsonProperty("code") String code, @JsonProperty("info") String info, @JsonProperty("data") T data) {
        super();
        this.code=code;
        this.info=info;
        this.data=data;
    }

    /**
     * 判断Result是否成功的快捷方法
     * @return
     */
    @JsonIgnore
    public boolean isSuccess(){
        return SUCCESS_CODE.equals(code);
    }

    /**
     * 断言返回是成功的，如果不成功，根据错误码抛出{@linkplain BaseBusinessException BaseBusinessException}
     * @return
     */
    public boolean assertSuccess(){
        if(!isSuccess()){
            throw new BaseBusinessException(code,info);
        }
        return true;
    }

    /**
     * 检查并返回data对象，如果code不是成功状态，抛出异常
     * @return
     */
    public T checkAndGetData(){
        if(!isSuccess()){
            throw new BaseBusinessException(code,info);
        }
        return data;
    }


    @Override
    public String toString(){
        return String.format("{\"code\":%s,\"info\":%s,\"data\":%s}",code,info,data);
    }

}
