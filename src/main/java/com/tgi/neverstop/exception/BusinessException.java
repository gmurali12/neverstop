package com.tgi.neverstop.exception;

import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;
	public String errorMessage;
	
	public BusinessException() {

	}

	public BusinessException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
