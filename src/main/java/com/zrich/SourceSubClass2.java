package com.zrich;

/**
 * Created by Administrator on 2017/7/24.
 */
public class SourceSubClass2 {

    /**
     * 外部商品编号
     */
    private String outerGoodsId;

    /**
     * 外部商品名称
     */
    private String outerGoodsName;

    private Long goodsNo;

    private String goodsName;

    private String goodsShortName;

    /** 销售单价  **/
    private Long channelSalePrice;

    public String getOuterGoodsId() {
        return outerGoodsId;
    }

    public SourceSubClass2 setOuterGoodsId(String outerGoodsId) {
        this.outerGoodsId = outerGoodsId;
        return this;
    }

    public String getOuterGoodsName() {
        return outerGoodsName;
    }

    public SourceSubClass2 setOuterGoodsName(String outerGoodsName) {
        this.outerGoodsName = outerGoodsName;
        return this;
    }

    public Long getGoodsNo() {
        return goodsNo;
    }

    public SourceSubClass2 setGoodsNo(Long goodsNo) {
        this.goodsNo = goodsNo;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public SourceSubClass2 setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public String getGoodsShortName() {
        return goodsShortName;
    }

    public SourceSubClass2 setGoodsShortName(String goodsShortName) {
        this.goodsShortName = goodsShortName;
        return this;
    }

    public Long getChannelSalePrice() {
        return channelSalePrice;
    }

    public SourceSubClass2 setChannelSalePrice(Long channelSalePrice) {
        this.channelSalePrice = channelSalePrice;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SourceSubClass2{");
        sb.append("outerGoodsId='").append(outerGoodsId).append('\'');
        sb.append(", outerGoodsName='").append(outerGoodsName).append('\'');
        sb.append(", goodsNo=").append(goodsNo);
        sb.append(", goodsName='").append(goodsName).append('\'');
        sb.append(", goodsShortName='").append(goodsShortName).append('\'');
        sb.append(", channelSalePrice=").append(channelSalePrice);
        sb.append('}');
        return sb.toString();
    }
}
