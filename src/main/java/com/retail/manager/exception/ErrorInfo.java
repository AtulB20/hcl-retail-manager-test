package com.retail.manager.exception;

public class ErrorInfo {
	public final String status;
    public final String message;

    public ErrorInfo(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
