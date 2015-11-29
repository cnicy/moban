package com.momix.sdk.parser.exception;

/**
 * 自定义异常类
 */
public class ApiException extends Exception {
	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String errorMsg;

	// ***默认***
	public ApiException() {
		super();
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiException(String message) {
		super(message);
	}

	public ApiException(Throwable cause) {
		super(cause);
	}

	// ***自定义***
	public ApiException(String errorCode, String errorMsg) {
		super(errorCode + ":" + errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}