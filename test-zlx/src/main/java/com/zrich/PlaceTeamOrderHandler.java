package com.zrich;

import com.alibaba.fastjson.JSONObject;
import com.sun.media.sound.SoftTuning;

/**
 * Created by Administrator on 2017/8/24.
 */
public class PlaceTeamOrderHandler {

    public String execute(String channelCode, String key, String thriftIP, Integer port) {
        String body = "{\n" +
                "  \"channelId\": \"10291\",\n" +
                "  \"quantity\": 1,\n" +
                "  \"applyDetails\": [\n" +
                "    {\n" +
                "      \"goodsNo\": \"T000004259\",\n" +
                "      \"quantity\": \"1\",\n" +
                "      \"channelSettlementPrice\": 1000,\n" +
                "      \"validStartDate\": \"2017-08-15\",\n" +
                "      \"validEndDate\": \"2017-08-31\",\n" +
                "      \"splits\": [\n" +
                "        {\n" +
                "          \"goodsNum\": \"1\",\n" +
                "          \"voucherNum\": 1,\n" +
                "          \"splitDetails\": [\n" +
                "            {\n" +
                "              \"certificateNo\": \"350301197905087425\",\n" +
                "              \"acceptMobileNo\": \"18574589658\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        ClientController controller = new ClientController();
        try {

            JSONObject jsonObject = controller.execute("PLACE_TEAM_ORDER", channelCode, body, key, thriftIP, port);
            String orderNo =  jsonObject.get("orderNo").toString();
//            System.out.println("OrderNo = " + orderNo);
            return orderNo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
