package com.landptf.tools;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

/** 
* @ClassName: MachineM 
* @Description: 机器系统相关信息工具类
* @author landptf
* @date 2015-8-25 下午8:35:58  
*/ 
public class MachineM {
	/**
	 * 获取android当前可用内存大小
	 * @param context
	 * @return
	 */
	public static String getAvailMemory(Context context) {// 获取android当前可用内存大小
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo mi = new MemoryInfo();
		am.getMemoryInfo(mi);
		return Formatter.formatFileSize(context, mi.availMem);// 将获取的内存大小规格化
	}

	/**
	 * 获得系统总内存
	 * @param context
	 * @return
	 */
	public static String getTotalMemory(Context context) {
		String str1 = "/proc/meminfo";// 系统内存信息文件
		String str2;
		String[] arrayOfString;
		long initial_memory = 0;
		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
			str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
			arrayOfString = str2.split("\\s+");
			initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
			localBufferedReader.close();

		} catch (IOException e) {
		}
		return Formatter.formatFileSize(context, initial_memory);// Byte转换为KB或者MB，内存大小规格化
	}
	
	/**
	 * 获得手机屏幕分辨率
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getHeightAndWidth(Activity context) {
		int width = context.getWindowManager().getDefaultDisplay().getWidth();
		int heigth = context.getWindowManager().getDefaultDisplay().getHeight();
		return width + "*" + heigth;
	}
	
	/**
	 * 获取屏幕高度
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getHeight(Activity context) {
		return context.getWindowManager().getDefaultDisplay().getHeight();
	}
	
	/**
	 * 获取屏幕宽度
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getWidth(Activity context) {
		return context.getWindowManager().getDefaultDisplay().getWidth();
	}
	/**
	 * 获得手机型号品牌等信息
	 * @param context
	 * @return
	 */
	public static String getBrandInfo(Context context) {
        String mtype = android.os.Build.MODEL; // 手机型号
        String mtyb= android.os.Build.BRAND;//手机品牌
        return "手机型号:"+mtype+", 手机品牌:"+mtyb;
    }
	
	/**
	 * 获取手机MAC地址
	 * 只有手机开启wifi才能获取到mac地址
	 * @param context
	 * @return
	 */
	public static String getMacAddress(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getMacAddress();
    }
}