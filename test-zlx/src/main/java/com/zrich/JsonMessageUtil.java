package com.zrich;

/**
 * <p>Description: </p>
 * @date 2015年7月15日
 * @author 何文 
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class JsonMessageUtil {

	/**
	 * 标志
	 */
	private boolean flag;

	private String errorCode;
	/**
	 * 信息
	 */
	private String message;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
