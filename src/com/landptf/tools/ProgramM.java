package com.landptf.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.PowerManager;


/**
 * 程序相关的工具类
 * @author landptf
 * @date 2015-6-19
 */
public class ProgramM {
	
	/**
	 * 获取当前运行的Activity界面的全称(包名+类名)
	 * @return
	 */
	public static String getActivityClassName(Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		return manager.getRunningTasks(1).get(0).topActivity.getClassName();
	}
	
	/**
	 * 获取当前运行的Activity界面的类名
	 * @return
	 */
	public static String getActivityShortClassName(Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		return manager.getRunningTasks(1).get(0).topActivity.getShortClassName();
	}
	
	/**
	 * 获取当前运行的Activity界面的包名
	 * @return
	 */
	public static String getActivityPackageName(Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		return manager.getRunningTasks(1).get(0).topActivity .getPackageName();
	}
	
	/**
	 * 判断程序是否背景运行中
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isBackgoundRunning(Context context) {
		boolean backgroundRunning = false;
		int myPid = android.os.Process.myPid();
		PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		if (powerManager.isScreenOn()) {
			ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			for (ActivityManager.RunningAppProcessInfo appProcess : manager .getRunningAppProcesses()) {
				if (appProcess.pid == myPid) {
					if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
						backgroundRunning = true;
						break;
					}
				}
			}
		} else{
			backgroundRunning = true;
		}
		return backgroundRunning;
	}
	
	/**
	 * 获取BaiduApiKey
	 * 
	 * @param context
	 * @param metaKey
	 * @return
	 */
	public static String getMetaValue(Context context, String metaKey) {
		Bundle metaData = null;
		String apiKey = null;
		if (context == null || metaKey == null) {
			return null;
		}
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
			if (null != ai) {
				metaData = ai.metaData;
			}
			if (null != metaData) {
				apiKey = metaData.getString(metaKey);
			}
		} catch (NameNotFoundException e) {
		}
		return apiKey;
	}
}
