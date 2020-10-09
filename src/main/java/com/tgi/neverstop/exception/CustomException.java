package com.tgi.neverstop.exception;

public class CustomException {
	private static final long serialVersionUID = 1L;
	public String errorMessage;
	
	public CustomException() {

	}

	public CustomException( String errorMessage) {
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
