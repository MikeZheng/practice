package com.zrich;

import java.util.Date;

public class ReqPacket {


    /**
     * 消息体密文
     */
    protected String encodeBody;

    /**
     * OTA编号 即渠道商ID
     */
    protected String distributorId;

    /**
     * 消息序列号
     */
    protected String sequenceId;

    /**
     * 签名
     */
    protected String signed;

    /**
     * 时间戳
     */
    protected Date timeStamp;

    /**
     * 交易码
     */
    protected String transactionCode;

    /**
     * 版本号
     */
    protected String version;

    public String getEncodeBody() {
        return encodeBody;
    }

    public void setEncodeBody(String encodeBody) {
        this.encodeBody = encodeBody;
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getSigned() {
        return signed;
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
