package com.zrich;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Arrays;

import com.sun.media.sound.SoftTuning;

/**
 * 
 * <p>Description: MD5算法基础类</p>
 * @date 2013-11-1
 * @author dyxiang
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Md5 {

	private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	/** 
	 * 转换字节数组为16进制字串 
	 * @param b 字节数组 
	 * @return 16进制字串 
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			int n = b[i];
			if (n < 0)
				n = 256 + n;
			int d1 = n / 16;
			int d2 = n % 16;
			resultSb.append(hexDigits[d1]);
			resultSb.append(hexDigits[d2]);
		}
		return resultSb.toString();
	}

	/**
	 * 计算MD5
	 * @param origin 原始字符串
	 * @return md5字符串；null：出错
	 */
	public static String encode(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	/**
	 * 验证MD5串
	 * @param origin 原始字符串
	 * @param md5 md5字符串
	 * @return true：正确；false：不正确
	 */
	public static boolean verify(String origin, String md5) {
		String tmp = encode(origin);
		try {
			if (tmp.equals(md5)) {
				return true;
			}
		} catch (Exception e) {
		}

		return false;
	}

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String text = "askldfjqweopruqiwepriqweporiqwjeporjqeoifjqpoiwefj";
        String encoded = Md5.encode(text);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(text.getBytes());
        System.out.println(Integer.toHexString(85));
        System.out.println(Arrays.toString(bytes));
        System.out.println(encoded);
        LocalDate localDate = LocalDate.of(1980, 2, 12);
        System.out.println(localDate);
        System.out.println(Integer.parseInt("01"));
    }



}
