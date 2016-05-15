package com.landptf.tools;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
* @ClassName: VerifyM 
* @Description:校验工具类 
* @author landptf
* @date 2016-5-14 下午4:49:52  
*/ 
public class VerifyM {
	private static final char[] enChar 							= new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private static final char[] numChar 						= new char[]{'0','1','2','3','4','5','6','7','8','9'};
	private static final String MOBILE_REGX_STRING  			= "^[1][3,4,5,7,8][0-9]{9}$"; 
	private static final String PHONE_ZONE_DES_REGX_STRING  	= "^[0][1-9]{2,3}-[0-9]{5,10}$"; 
	private static final String PHONE_REGX_STRING  				= "^[1-9]{1}[0-9]{5,8}$"; 
	private static final String EMAIL_REGX_STRING  				= "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,3}$"; 
	
	static {
		Arrays.sort(enChar);
		Arrays.sort(numChar);
	}
	
	/**
	 * 判断是否是英文字母或者数字组成
	 * @param test
	 * @return 验证通过返回true
	 */
	public static boolean isEnOrNum(String test) {
		for (char ch : test.toCharArray()) {
			if (Arrays.binarySearch(enChar, ch) < 0 && Arrays.binarySearch(numChar, ch) < 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断是否是英文字母组成
	 * @param test
	 * @return 验证通过返回true
	 */
	public static boolean isEnglish(String test) {
		for (char ch : test.toCharArray()) {
			if (Arrays.binarySearch(enChar, ch) < 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断是否是数字组成
	 * @param test
	 * @return 验证通过返回true
	 */
	public static boolean isNumber(String test) {
		for (char ch : test.toCharArray()) {
			if (Arrays.binarySearch(numChar, ch) < 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 手机号验证
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile(MOBILE_REGX_STRING); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 电话号码验证
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile(PHONE_ZONE_DES_REGX_STRING); // 验证带区号的
		p2 = Pattern.compile(PHONE_REGX_STRING); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}
	/**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        boolean flag = false;
        try{
                String check = EMAIL_REGX_STRING;
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(email);
                flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    }
}
