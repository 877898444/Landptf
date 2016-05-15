package com.landptf.tools;

import java.security.MessageDigest;
import java.util.UUID;

import android.annotation.SuppressLint;


/**
 * EncryptUtil.java
 *
 * MD5加加密
 *	 
 */
public class EncryptM {
	
	@SuppressLint("TrulyRandom")
	private static java.security.SecureRandom random = new java.security.SecureRandom();
	/**
	 * 加密
	 * @param srcStr
	 * @return
	 */
	public static String MD5(String key, String str){
		return hash("MD5",key+str);
	}
	
	public static String UUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();// 鏍囧噯UUID
	}
	
	public static String UUID2() {
		String str = UUID();
		String temp = str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return temp;
	}

	public static String[] UUID2Arr(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = UUID2();
		}
		return ss;
	}

	/**
	 * 加密
	 * @param srcStr
	 * @return
	 */
	public static String md5(String srcStr){
		return hash("MD5", srcStr);
	}
	
	public static String sha1(String srcStr){
		return hash("SHA-1", srcStr);
	}
	
	public static String sha256(String srcStr){
		return hash("SHA-256", srcStr);
	}
	
	public static String sha384(String srcStr){
		return hash("SHA-384", srcStr);
	}
	
	public static String sha512(String srcStr){
		return hash("SHA-512", srcStr);
	}
	
	public static String hash(String algorithm, String srcStr) {
		try {
			StringBuilder result = new StringBuilder();
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
			for (byte b : bytes) {
				String hex = Integer.toHexString(b&0xFF);
				if (hex.length() == 1)
					result.append("0");
				result.append(hex);
			}
			return result.toString();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String toHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toHexString(b&0xFF);
			if (hex.length() == 1)
				result.append("0");
			result.append(hex);
		}
		return result.toString();
	}
	
	/**
	 * md5 128bit 16bytes
	 * sha1 160bit 20bytes
	 * sha256 256bit 32bytes
	 * sha384 384bit 48bites
	 * sha512 512bit 64bites
	 */
	public static String generateSalt(int numberOfBytes) {
		byte[] salt = new byte[numberOfBytes];
		random.nextBytes(salt);
		return toHex(salt);
	}
}
