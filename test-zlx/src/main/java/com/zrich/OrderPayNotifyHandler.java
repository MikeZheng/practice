package com.zrich;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/8/24.
 */
public class OrderPayNotifyHandler {

    public JSONObject execute(String channelCode, String key, String thriftIP, Integer port, String orderNo) {
        String body = "{\n" +
                "    \"orderNo\": \"" +orderNo + "\",\n" +
                "    \"payTime\": \"20170824195702174\",\n" +
                "    \"payModel\": \"10000000\",\n" +
                "    \"payDatas\": [\n" +
                "        {\n" +
                "            \"type\": \"1\",\n" +
                "            \"refPaySerialNo\": \"1212323123\",\n" +
                "            \"paySerialNo\": \"3333333333\",\n" +
                "            \"payAccount\": \"1|1|06\",\n" +
                "            \"amount\": 100\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        ClientController controller = new ClientController();
        try {

            JSONObject jsonObject = controller.execute("ORDER_PAY_NOTIFY", channelCode, body, key, thriftIP, port);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
