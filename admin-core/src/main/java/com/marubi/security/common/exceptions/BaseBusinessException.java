package com.marubi.security.common.exceptions;

import com.marubi.security.common.codes.ErrorCode;
import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 通用业务异常
 */
@Data
public class BaseBusinessException extends RuntimeException {


	private static final long serialVersionUID = 3808478463414569598L;
	/**
	 * 异常Code
	 */
	private String errorCode;

	/**
	 * 异常信息
	 */
	private String erroMessage;

	/**
	 * 异常堆栈
	 */
	private String exStackTrace;

	public BaseBusinessException(String errorCode, String errorMessage, Object... msgArgs) {
		super();
		this.errorCode = errorCode;
		if (msgArgs != null && msgArgs.length>0) {
			this.erroMessage = String.format(errorCode, msgArgs);
		} else {
			this.erroMessage = errorMessage;
		}
	}


	public BaseBusinessException(ErrorCode code, Object... msgArgs) {
		super();
		if (code != null) {
			this.errorCode = code.toString();
			if (msgArgs != null && msgArgs.length>0) {
				this.erroMessage = String.format(code.getMsg(), msgArgs);
			} else {
				this.erroMessage = code.getMsg();
			}
		}
	}

	public BaseBusinessException(ErrorCode code, Throwable ex, Object... msgArgs) {
		super(ex);
		if (code != null) {
			this.errorCode = code.toString();
			if (msgArgs != null && msgArgs.length>0) {
				this.erroMessage = String.format(code.getMsg(), msgArgs);
			} else {
				this.erroMessage = code.getMsg();
			}
			if (ex != null) {
				this.exStackTrace = ExceptionUtils.getStackTrace(ex);
			}
		}
	}

	@Override
	public String getMessage() {
		return erroMessage!=null?erroMessage:super.getMessage();
	}


}
