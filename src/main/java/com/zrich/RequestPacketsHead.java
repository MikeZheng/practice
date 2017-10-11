package com.zrich;



/**
 * <p>Description: </p>
 * @date 2014年12月12日
 * @author 王方威
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class RequestPacketsHead  {

    /**
     * 版本号
     */
    private String version;

    /**
     * 报文时间戳
     */
    private String timeStamp;

    /**
     * 报文消息序列号
     */
    private String sequenceId;

    /**
     * 设备号
     */
    private String deviceNo;

    /**
     * 交易码
     */
    private ToPayTradeNo tradeNo;

    /**
     * 消息签名
     */
    private String signed;

    /**
     * 密文的报文体
     */
    private String packetBody;



}
