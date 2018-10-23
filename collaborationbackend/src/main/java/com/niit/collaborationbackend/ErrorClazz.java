package com.niit.collaborationbackend;

public class ErrorClazz {
	private int errorCode;
	private String errorMsg;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ErrorClazz(int errorCode, String errorMsg) 
	{
		this.errorCode=errorCode;
		this.errorMsg=errorMsg;
	}
}
