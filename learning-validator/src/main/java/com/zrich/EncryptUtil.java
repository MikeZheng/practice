package com.zrich;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {
  private static final String SITE_WIDE_SECRET = "35637289C2354E38B5FC743983DFBDD6";
  private static final PasswordEncoder ENCODER = new StandardPasswordEncoder("35637289C2354E38B5FC743983DFBDD6");

  public EncryptUtil() {
  }

  public static String encrypt(String rawPassword) {
    return ENCODER.encode(rawPassword);
  }

  public static boolean match(String rawPassword, String password) {
    return ENCODER.matches(rawPassword, password);
  }

  public static void encryptPassword(String password) throws IOException, NoSuchAlgorithmException {
    System.out.println(base64(md5(password)));
  }



  public static String base64(byte[] textByte) throws IOException {
    final BASE64Encoder encoder = new BASE64Encoder();
//编码
    final String encodedText = encoder.encode(textByte);
    System.out.println(encodedText);
    return encodedText;
  }

  /**
   * MD5加密16位
   * @param encryptStr 要加密数据
   * @return 返回16位加密结果
   * ZhaoLi
   */
  public static String md5Encrypt16(String encryptStr)
  {
    return md5Encrypt32(encryptStr).substring(8, 24);
  }

  /**
   * MD5加密32位
   * @param encryptStr 要加密数据
   * @return 32位加密结果
   * ZhaoLi
   */
  public static String md5Encrypt32(String encryptStr)
  {
    MessageDigest md5;
    try
    {
      md5 = MessageDigest.getInstance("MD5");
      byte[] md5Bytes = md5.digest(encryptStr.getBytes());
      StringBuffer hexValue = new StringBuffer();
      for (int i = 0; i < md5Bytes.length; i++)
      {
        int val = (md5Bytes[i]) & 0xff;
        if (val < 16)
        {
          hexValue.append("0");
        }
        hexValue.append(Integer.toHexString(val));
      }
      return hexValue.toString().toLowerCase();
    } catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * 结合base64实现md5加密
   * @param msg 待加密字符串
   * @return 获取md5后转为base64
   * @throws Exception
   */
  public static String md5EncryptBase64(String msg) throws Exception
  {
    return msg == null ? null : base64Encode(md5(msg));
  }

  /**
   * 将byte[]转为各种进制的字符串
   * @param bytes byte[]
   * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
   * @return 转换后的字符串
   */
  public static String binary(byte[] bytes, int radix)
  {
    return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
  }

  /**
   * base 64 encode
   * @param bytes 待编码的byte[]
   * @return 编码后的base 64 code
   */
  public static String base64Encode(byte[] bytes)
  {
    return new BASE64Encoder().encode(bytes);
  }

  /**
   * base 64 decode
   * @param base64Code 待解码的base 64 code
   * @return 解码后的byte[]
   * @throws Exception
   */
  public static byte[] base64Decode(String base64Code)
  {
    try
    {
      return base64Code == null ? null : new BASE64Decoder().decodeBuffer(base64Code);
    } catch (IOException e)
    {
      throw new RuntimeException("报错内容", e);
    }
  }

  /**
   * 获取byte[]的md5值
   * @param bytes byte[]
   * @return md5
   * @throws Exception
   */
  public static byte[] md5(byte[] bytes)
  {
    MessageDigest md;
    try
    {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e)
    {
      throw new RuntimeException("报错内容", e);
    }
    md.update(bytes);
    return md.digest();
  }

  /**
   * 获取字符串md5值
   * @param msg
   * @return md5
   * @throws Exception
   */
  public static byte[] md5(String msg)
  {
    return msg == null ? null : md5(msg.getBytes());
  }

  public static void main(String[] args) throws Exception {
    System.out.println(match("NjU0MzIx", "b07f6e0b42843258ad2b37c9416a4b1801f9feabcfa95f4c6b20a4aacc93a4eb29982887597bd1d3"));
    System.out.println(match("OTZlNzkyMTg5NjVlYjcyYzkyYTU0OWRkNWEzMzAxMTI=", "4d6c0fd52bce3a6c0b289808f30e5d0b587f8d4d13830fc3b7d48adb2597c9ee1ee93005b769e908"));
    System.out.println(EncryptUtil.encrypt("OTZlNzkyMTg5NjVlYjcyYzkyYTU0OWRkNWEzMzAxMTI="));
    System.out.println(EncryptUtil.encrypt("123456"));
    System.out.println(Utf8.encode("654321"));
    encryptPassword(new String(Utf8.encode("654321")));
    base64(md5("111111"));
    System.out.println(md5EncryptBase64("111111"));
    System.out.println(new String(md5("111111")));
    System.out.println(new String(md5Encrypt32("111111")));
    System.out.println(new String(base64Decode("OTZlNzkyMTg5NjVlYjcyYzkyYTU0OWRkNWEzMzAxMTI=")));
    System.out.println(base64(md5Encrypt32("000000").getBytes()));
    System.out.println(base64(md5Encrypt32("111111").getBytes()));
    System.out.println(base64(md5Encrypt32("222222").getBytes()));
    System.out.println(base64(md5Encrypt32("333333").getBytes()));
    System.out.println(base64(md5Encrypt32("123456").getBytes()));
  }
}
