package com.zrich;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * <p>Description: </p>
 * @author 郑振富
 * @version 1.0
 *          <p>Company:Mopon</p>
 *          <p>Copyright:Copyright(c)2017</p>
 */
public class DecryptTest {

    private static final String SECRET_KEY = "MOPON_TH";

    public static void main(String[] args) throws Exception {
        String cookie="E53C8B3F430AB2F60394CFE112C6041D784363D3263A490210C4EAF39743B1E0BD5C53F2AC170CF4FB631E1730B616EFEF627128A5C33F1E10C4EAF39743B1E0BD5C53F2AC170CF4D64D747F96C1001991201B316A3156BE9FFB443E489AF3D41CEDB17FA29AA34CD63ECC9B99D51757E7408FA2D74B35ED4FC7C9A0E163E62C547A24C38447D6E22C10EB8288D3D61993B552614BD37B3C43D0D4A51E4510E8E9242FFC70B75E81E0E2631DF29FEAFC49B1649E168E91869F7B834C0C9155379EED373EF71BF2792E9DED627E5E422330C4D6F76079EB7851F611340C026373D3FFE10C080E79EF06BA56A5FEC7A1AAAB2DDDD6DE5DEE47C030FD85FFDA13AB01AE08CE8159274768644D1D38E273AEF1BE621B61EE3463D591FB7AFDCBF74F";

        String json = new String(decrypt(hex2byte(cookie.getBytes()), SECRET_KEY.getBytes()), "UTF-8");
        System.out.println(json);
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 返回转换指定算法的秘密密钥的SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(src);
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
}
