package com.zrich;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * 
 * <p>Description: DES加密解密 </p>
 * @date 2013-11-1
 * @author dyxiang
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class DesSecret {

	/**
	 * 密钥，长度必须是8的倍数
	 * 如果系统中存在旧版本的数据，则此值不能修改，否则在进行密码解析的时候出错
	 */
	private static final String SECRET_KEY = "MOPON_TH";
	
	/**
	 * 算法
	 */
	private final static String ALGORITHM = "DES";	
	
	/**
	 * 
	 * 方法用途: 加密<br>
	 * 实现步骤: <br>
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 返回转换指定算法的秘密密钥的SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}
	
	/**
	 * 
	 * 方法用途:解密 <br>
	 * 实现步骤: <br>
	 * @param src	数据源
	 * @param key	密钥，长度必须是8的倍数
	 * @return		返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 返回转换指定算法的秘密密钥的SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * 
	 * 方法用途: 二进制转字符串<br>
	 * 实现步骤: <br>
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		String stmp = null;
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				sb.append('0').append(stmp);
			else
				sb.append(stmp);
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 
	 * 方法用途: <br>
	 * 实现步骤: <br>
	 * @param b
	 * @return
	 */
	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	
	/**
	 * 
	 * 方法用途: 解密<br>
	 * 实现步骤: <br>
	 * @param cipherText 密文
	 * @return
	 */
	public final static String decrypt(String cipherText) throws Exception{
		return new String(decrypt(hex2byte(cipherText.getBytes("UTF-8")), SECRET_KEY.getBytes()));
	}
	
	/**
	 * 
	 * 方法用途: 解密<br>
	 * 实现步骤: <br>
	 * @param cipherText	密文
	 * @param secretKey	密钥
	 * @return
	 */
	public final static String decrypt(String cipherText, String secretKey) throws Exception{
		return new String(decrypt(hex2byte(cipherText.getBytes("UTF-8")), secretKey.getBytes()),"UTF-8");
	}
	
	/**
	 * 
	 * 方法用途:加密 <br>
	 * 实现步骤: <br>
	 * @param password	没有加密的文本
	 * @return
	 */
	public final static String encrypt(String plainText) throws Exception{
		return byte2hex(encrypt(plainText.getBytes("UTF-8"), SECRET_KEY.getBytes()));
	}	
	
	/**
	 * 
	 * 方法用途:加密 <br>
	 * 实现步骤: <br>
	 * @param plainText	没有加密的文本
	 * @param secretKey	密钥
	 * @return
	 */
	public final static String encrypt(String plainText, String secretKey) throws Exception{
		return byte2hex(encrypt(plainText.getBytes("UTF-8"), secretKey.getBytes()));
	}

	public static void main(String[] args) {
		try {
			System.out.println(decrypt("77766D554F9E17BD06C33030713A1D3C6DFCB870FF3550D4F772DEB82E6D55C65280F9245C1F2AA3"));
			System.out.println(encrypt("123", "7BC522FB3B0F4D36A578C906AB9407BC"));
			System.out.println(encrypt(null));
		} catch (Exception e) {
			System.out.println(System.currentTimeMillis());
			e.printStackTrace();
		}
	}
	
}