package com.landptf.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class CallM {
	/**
	 * 直接拨打电话
	 * @param context
	 * @param number
	 */
	public static void callToNumber(Context context, String number) {
		Intent tel = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number));
		context.startActivity(tel);
	}
	
	/**
	 * 拨打电话之前显示拨号界面
	 * @param context
	 * @param number
	 */
	public static void callToNumberInDialog(Context context, String number){
		Intent tel = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+number));
		context.startActivity(tel);
	}
}
