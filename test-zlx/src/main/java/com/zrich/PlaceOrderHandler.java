package com.zrich;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/8/25.
 */
public class PlaceOrderHandler {

    public String execute(String channelCode, String key, String thriftIP, Integer port) {

        StringBuilder body = new StringBuilder();

//        File.class.getResource("place_order.json");

        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/place_order.json")));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                body.append(tempString);
//                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }



        ClientController controller = new ClientController();
        try {
            JSONObject jsonObject = controller.execute("PLACE_ORDER", channelCode, body.toString(), key, thriftIP, port);
            System.out.println(jsonObject);
            String orderNo = jsonObject.get("orderNo").toString();
            System.out.println("OrderNo = " + orderNo);
            return orderNo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
