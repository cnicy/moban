package com.gehua.moban.common.exception;

public class BizException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3148833208442176697L;

	public BizException() {
		super();
	}
	public BizException(String msg) {
		super(msg);
	}
	
	public BizException(String message, Throwable cause){
		super(message, cause);
	}
}