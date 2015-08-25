package com.landptf.tools;

import android.content.Context;
import android.widget.Toast;

/** 
* @ClassName: ToastM 
* @Description: Toast工具类
* @author landptf
* @date 2015-8-16 下午4:49:01  
*/ 
public class ToastM {
	public static boolean isShow = true;

	/**
	 * 短时间提示Toast 
	 * 
	 * param context 
	 * @param message 
	 */
	public static void showShort(Context context, CharSequence message) {
		try {
			if (isShow)
				Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
		}
	}

	/**
	 * 短时间提示Toast 
	 * 
	 * param context 
	 * @param message 
	 */
	public static void showShort(Context context, int message) {
		try {
			if (isShow)
				Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
		}
	}

	/**
	 * 长时间提示Toast 
	 * 
	 * param context 
	 * @param message 
	 */
	public static void showLong(Context context, CharSequence message) {
		try {
			if (isShow)
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		} catch (Exception e) {
		}
	}

	/**
	 * 长时间提示Toast 
	 * 
	 * param context 
	 * @param message 
	 */
	public static void showLong(Context context, int message) {
		try {
			if (isShow)
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		} catch (Exception e) {
		}
	}

	/**
	 * 自定义提示时间
	 * 
	 * param context 
	 * @param message 
	 */
	public static void show(Context context, CharSequence message, int duration) {
		try {
			if (isShow)
				Toast.makeText(context, message, duration).show();
		} catch (Exception e) {
		}
	}

	/**
	 * 自定义提示时间
	 * 
	 * param context 
	 * @param message 
	 */
	public static void show(Context context, int message, int duration) {
		try {
			if (isShow)
				Toast.makeText(context, message, duration).show();
		} catch (Exception e) {
		}
	}
}
