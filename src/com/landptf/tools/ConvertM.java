package com.landptf.tools;

import android.content.Context;
import android.util.TypedValue;

/** 
* @ClassName: ConvertM 
* @Description: 转换工具类
* @author landptf
* @date 2016-5-8 下午1:30:52  
*/ 
public class ConvertM {
	
	/**
	 * @param context
	 * @param dpVal
	 * @return
	 */
	public static int dp2px(Context context, float dpVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}
}
