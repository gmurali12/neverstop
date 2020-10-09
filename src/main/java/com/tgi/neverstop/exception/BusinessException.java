package com.tgi.neverstop.exception;

import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;
	public String errorMessage;
	public String errorcode;

	public BusinessException() {

	}

	public BusinessException(String errorcode, String errorMessage) {

		this.errorcode = errorcode;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
