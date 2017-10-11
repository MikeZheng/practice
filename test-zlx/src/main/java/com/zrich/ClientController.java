package com.zrich;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ClientController {


	public static final int TIMEOUT = 30000;

	public JSONObject execute(String transactionCode, String appCode, String body,
			String secretKey, String serverIp, Integer serverPort) throws Exception {

		RequestPacketsHead requestHead = new RequestPacketsHead();
		requestHead.setTransactionCode(transactionCode);
		requestHead.setAppCode(appCode);
		requestHead.setTimeStamp(getCurrentDateTime());
		requestHead.setVersion("1.0");
		requestHead.setSequenceId(getCurrentDateTime());

		com.alibaba.fastjson.JSONObject packet = new com.alibaba.fastjson.JSONObject();
        packet.put("head", requestHead);
        packet.put("body", OfflinePacketsService.encryptBody(body, secretKey));

		String result = startClient(packet.toJSONString(), serverIp, serverPort, secretKey);

        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject;
	}

    public static String getCurrentDateTime() {
        SimpleDateFormat dataTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dataTimeFormat.format(date);
    }


    public String startClient(String requestStr, String serverIp, int serverPort, String secretKey) {
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(serverIp, serverPort, TIMEOUT));

			TProtocol protocol = new TBinaryProtocol(transport);
			OfflineWindowPortalWebService.Client client = new OfflineWindowPortalWebService.Client(protocol);
			transport.open();

            String result =  client.doTransaction(requestStr);

            com.alibaba.fastjson.JSONObject resultJson = com.alibaba.fastjson.JSONObject.parseObject(result);

            if (null != result) {
                String responseBody = resultJson.getString("body");
                return OfflinePacketsService.decryptBody(responseBody, secretKey);
            }


		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
		return "";
	}
}
