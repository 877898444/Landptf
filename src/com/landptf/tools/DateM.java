package com.landptf.tools;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间帮助类
 * @author landptf
 * @date 2015-5-22
 */
@SuppressLint("SimpleDateFormat")
public class DateM {
	private static String parttern = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获取当前时间，格式"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getNow(){
		return getNow(parttern);
	}
	
	/**
	 * 获取当前时间，自定义格式
	 * @param parttern
	 * @return
	 */
	public static String getNow(String parttern){
		SimpleDateFormat df = new SimpleDateFormat(parttern);//璁剧疆鏃ユ湡鏍煎紡
		return df.format(new Date());
	}
	
	/**
	 * 一周前的日期
	 * @return
	 */
	public static String lastWeek() {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) - 6;

		if (day < 1) {
			month -= 1;
			if (month == 0) {
				year -= 1;
				month = 12;
			}
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				day = 30 + day;
			} else if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12) {
				day = 31 + day;
			} else if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
					day = 29 + day;
				else
					day = 28 + day;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";
		return y + "-" + m + "-" + d;
	}
	
	/**
	 * 获得几个月前的日期
	 * @param allMonth
	 * @return
	 */
	public static String lastMonth(int allMonth) {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date))
				- allMonth;
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
		if (month <= 0) {
			int yearFlag = (month * (-1)) / 12 + 1;
			int monthFlag = (month * (-1)) % 12;
			year -= yearFlag;
			month = monthFlag * (-1) + 12;
		} else if (day > 28) {
			if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29;
				} else
					day = 28;
			} else if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& day == 31) {
				day = 30;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";
		return y + "-" + m + "-" + d;
	}
}
