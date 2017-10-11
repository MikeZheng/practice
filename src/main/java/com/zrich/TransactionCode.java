package com.zrich;

/**
 * Created by Administrator on 2017/7/21.
 */
public enum  TransactionCode {

    /**
     * 下单
     */
    PLACE_ORDER("PLACE_ORDER"),

    /**
     * 支付通知
     */
    ORDER_PAY_NOTIFY("ORDER_PAY_NOTIFY"),

    /**
     * 取消订单
     */
    CANCEL_ORDER("CANCEL_ORDER"),

    /**
     * 获取签约渠道
     */
    SERVICE_OP_CHANNEL_LIST("SERVICE_OP_CHANNEL_LIST"),

    /**
     * 订单详情
     */
    ORDER_DETAIL("ORDER_DETAIL"),

    /**
     * 凭证详情
     */
    VOUCHER_DETAIL("VOUCHER_DETAIL"),

    /**
     * 预订单列表
     */
    QUERY_APPLY_ORDER_LIST("QUERY_APPLY_ORDER_LIST"),

    ;
    private String code;

    public String getCode() {
        return this.code;
    }

    TransactionCode(String code) {
        this.code = code;
    }

    public static TransactionCode codeOf(String code) {
        TransactionCode[] codes = TransactionCode.values();
        int index = 0;
        while (index < codes.length) {
            if (codes[index].code.equals(code)) {
                return codes[index];
            }
            index++;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(codeOf("asdfsdf"));
    }
}

