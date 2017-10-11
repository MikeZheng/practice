package com.zrich;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
public class SourceClass2 {

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

    private List<SourceSubClass2> list;

    public String getApplyNo() {
        return applyNo;
    }

    public SourceClass2 setApplyNo(String applyNo) {
        this.applyNo = applyNo;
        return this;
    }

    public Integer getHadExchangeTicket() {
        return hadExchangeTicket;
    }

    public SourceClass2 setHadExchangeTicket(Integer hadExchangeTicket) {
        this.hadExchangeTicket = hadExchangeTicket;
        return this;
    }

    public String getExchangeTime() {
        return exchangeTime;
    }

    public SourceClass2 setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
        return this;
    }

    public Long getChannelCode() {
        return channelCode;
    }

    public SourceClass2 setChannelCode(Long channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SourceClass2 setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public List<SourceSubClass2> getList() {
        return list;
    }

    public SourceClass2 setList(List<SourceSubClass2> list) {
        this.list = list;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SourceClass2{");
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
