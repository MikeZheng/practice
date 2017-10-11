package com.zrich;

/**
 * Created by Administrator on 2017/7/21.
 */
public enum  TransactionCode {

    /**
     * �µ�
     */
    PLACE_ORDER("PLACE_ORDER"),

    /**
     * ֧��֪ͨ
     */
    ORDER_PAY_NOTIFY("ORDER_PAY_NOTIFY"),

    /**
     * ȡ������
     */
    CANCEL_ORDER("CANCEL_ORDER"),

    /**
     * ��ȡǩԼ����
     */
    SERVICE_OP_CHANNEL_LIST("SERVICE_OP_CHANNEL_LIST"),

    /**
     * ��������
     */
    ORDER_DETAIL("ORDER_DETAIL"),

    /**
     * ƾ֤����
     */
    VOUCHER_DETAIL("VOUCHER_DETAIL"),

    /**
     * Ԥ�����б�
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

