package com.accenture.fsadd.core;

/**
 * System Exception
 * 
 *
 */
public class SystemException extends RuntimeException {

	/**
	 * UIDs
	 */
	private static final long serialVersionUID = -3991023186507184924L;
	
	private String errorCode;
	
	public SystemException(){
		super();
		
	}
	
	public SystemException(String errorCode, String message,Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	public SystemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}


	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}


	public SystemException(String message) {
		super(message);
	}


	public SystemException(Throwable cause) {
		super(cause);
	}


	public String getErrorCode() {
		return errorCode;
	}


	

}
