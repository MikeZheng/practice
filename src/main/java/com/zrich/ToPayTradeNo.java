package com.zrich;


/**
 * <p>Description: </p>
 * @date 2014年12月16日
 * @author 王方威
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public enum ToPayTradeNo {

    /**
     * 初始化交易码
     */
    INIT_TERMINAL,

    /**
     *登录签到
     */
    USER_LOGIN,

    /**
     * 获取预订单
     */
    QUERY_APPLICATION,

    /**
     * 支付预订单
     */
    PAY_APPLICATION,

    /**
     * 重打印授权
     */
    REPRINT_AUTHORIZE,

    /**
     * 暂停交易
     */
    PADDING_TRAN,

    /**
     * 激活交易
     */
    ACTIVE_TRAN,

    /**
     * 日结单
     */
    DAY_STATEMENT,

    /**
     * 闸机验票
     */
    V_GATEWAY_CHECK,

    /**
     * 闸机过闸消费
     */
    V_GATEWAY_CONFIRM,

    /**
     * 闸机采集
     */
    V_GATEWAY_GATHER,

    /**
     * POS验票
     */
    V_POS_CHECK,

    /**
     * POS交易确认
     */
    V_POS_CONFIRM,

    /**
     * POS换票
     */
    V_POS_EXCHANGE,

    /**
     * POS重打印
     */
    V_POS_REPRINT,

    /**
     * 操作员签退
     */
    USER_LOGIN_OUT,

    /**
     * 媒介核对
     */
    V_CHECK_MEDIA,

    /**
     * 闸机验票多条
     */
    V_GATEWAY_CHECK_MULTI,

    /**
     * 凭证详情
     */
    V_GATEWAY_VOUCHER_DETAIL,

    /**
     * POS凭证详情
     */
    V_POS_VOUCHER_DETAIL,
    ;




}
