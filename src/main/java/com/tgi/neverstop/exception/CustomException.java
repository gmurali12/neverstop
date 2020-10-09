package com.tgi.neverstop.exception;

public class CustomException {
	private static final long serialVersionUID = 1L;
	public String errorMessage;
	public String errorcode;

	public CustomException() {

	}

	public CustomException(String errorcode, String errorMessage) {

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
