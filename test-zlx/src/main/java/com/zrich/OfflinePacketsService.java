package com.zrich;

/**
 * 线下报文通用服务类，
 * <p>Description: 负责报文的通用服务，包括：输入输出报文(初始化报文和检测接收到的报文)；检测报文签名 </p>
 * @date 2017年1月6日
 * @author 张驰 
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2017</p>
 */
public class OfflinePacketsService {

    public static String encryptBody(String body, String secretKey)  {
        try {
            return Base64.encode(DesSecret.encrypt(body, secretKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decryptBody(String encodeBody, String secretKey)  {
        try {
            return DesSecret.decrypt(Base64.decode(encodeBody), secretKey);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "";
    }
}
