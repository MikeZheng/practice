package com.zrich;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
public class SourceClass1 {

    /** 预订单编号 **/
    private String applyNo;

    /** 是否已取票（0:未取票 1:已取票） **/
    private Integer hadExchangeTicket;

    /** 取票时间 **/
    private String exchangeTime;

    /** 渠道商代码 **/
    private Long channelCode;

    /** 渠道商名称 **/
    private String channelName;

    private List<SourceSubClass1> list;

    public String getApplyNo() {
        return applyNo;
    }

    public SourceClass1 setApplyNo(String applyNo) {
        this.applyNo = applyNo;
        return this;
    }

    public Integer getHadExchangeTicket() {
        return hadExchangeTicket;
    }

    public SourceClass1 setHadExchangeTicket(Integer hadExchangeTicket) {
        this.hadExchangeTicket = hadExchangeTicket;
        return this;
    }

    public String getExchangeTime() {
        return exchangeTime;
    }

    public SourceClass1 setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
        return this;
    }

    public Long getChannelCode() {
        return channelCode;
    }

    public SourceClass1 setChannelCode(Long channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SourceClass1 setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public List<SourceSubClass1> getList() {
        return list;
    }

    public SourceClass1 setList(List<SourceSubClass1> list) {
        this.list = list;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SourceClass1{");
        sb.append("applyNo='").append(applyNo).append('\'');
        sb.append(", hadExchangeTicket=").append(hadExchangeTicket);
        sb.append(", exchangeTime='").append(exchangeTime).append('\'');
        sb.append(", channelCode=").append(channelCode);
        sb.append(", channelName='").append(channelName).append('\'');
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
