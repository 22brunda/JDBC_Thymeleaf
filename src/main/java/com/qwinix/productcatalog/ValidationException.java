package com.qwinix.productcatalog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ValidationException extends RuntimeException {
	public ValidationException(String msg) {
		super(msg);
	}
	
	public ValidationException(String msg, String errorSource) {
		super(msg);
		this.errorSource = errorSource;
	}
	
	private String errorSource;
	
	public String getErrorSource() {
		return errorSource;
	}
	public void setErrorSource(String errorSource) {
		this.errorSource = errorSource;
	}
	
}
