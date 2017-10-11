package com.zrich;

public abstract class PacketsHead extends JsonFormatPackets {

	/** 版本号 */
	private String version;

	/**消息序列号**/
	private String sequenceId;

	/** 报文时间戳  */
	private String timeStamp;

	/**交易码**/
	private String transactionCode;

	/**应用编码（知旅行平台分配的分销编码）**/
	private String appCode;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	@Override
	public String toString() {
		return "PacketsHead [version=" + version + ", sequenceId=" + sequenceId + ", timeStamp=" + timeStamp
				+ ", transactionCode=" + transactionCode + ", appCode=" + appCode + "]";
	}

}
