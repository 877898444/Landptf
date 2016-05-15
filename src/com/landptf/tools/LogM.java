package com.landptf.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import android.os.Build;
import android.os.Environment;
import android.util.Log;

public class LogM {
	public static String VersionName = "";//客户端程序的版本名称
	public static int VersionCode = 0;//客户端程序的版本号
    private static String SDCARD_LOG_PATH = "/kingsoft/share/logs/";//文件路径
    private static String LOGNAME = "logPopular.log";
	/**
	 * 构造函数
	 * @param logName   异常类名称
	 * @param s 异常信息
	 * @category 2013-3-19  下午1:39:07
	 * **/
	public static void writeT(String logName, String s){
		StringBuilder message =  new StringBuilder();//异常信息
		message.append("【版本："+VersionName+";版本号:"+VersionCode+"机型："+Build.MODEL+"】\n");
		message.append("【时间："+DateM.getNow()+"】\n");
		message.append("【"+logName+"】\n"+s);
		message.append("\n\n");
		//保存日志
		SdcardLogs(message);
	}
	
	/**
	 * 构造函数
	 * @param logName   异常类名称
	 * @param exception 异常信息
	 * @category 2013-3-19  下午1:39:07
	 * **/
	public static void writeE(String logName, Exception exception){
		StringBuilder message =  new StringBuilder();//异常信息
		message.append("【版本："+VersionName+";版本号:"+VersionCode+"机型："+Build.MODEL+"】\n");
		message.append("【时间："+DateM.getNow()+"】\n");
		message.append("【"+logName+"】\n"+LogStackTrace(exception));
		message.append("\n\n");
		//保存日志
		SdcardLogs(message);
	}
	
	/**
	 * 异常的StackTrace信息格式化为String
	 * @param throwable 异常
	 * @return String 异常信息 
	 * @category   2013-3-19  下午1:39:07
	 * **/
	private static String LogStackTrace(Throwable throwable) {  
	    if(throwable==null) return "";  
	    String logs = throwable.getStackTrace().toString();  
	    try {  
	        Writer writer = new StringWriter();  
	        PrintWriter printWriter = new PrintWriter(writer);  
	        throwable.printStackTrace(printWriter);       
	        printWriter.flush();  
	        writer.flush();  
	        logs = writer.toString();  
	        printWriter.close();
	        writer.close();
	    } catch (Exception e) {
	        e.printStackTrace();  
	    }
	    return logs;
	}
	/**
	 * 记录到SDCard
	 * @param message
	 */
	public synchronized static void SdcardLogs(StringBuilder message) {
		try {
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// 存在sdcard
				// 判断是否存在日志文件
				// 获取跟目录
				File logFile = new File(Environment.getExternalStorageDirectory(),SDCARD_LOG_PATH);
				logFile.mkdirs();
				// 文件
				logFile = new File(Environment.getExternalStorageDirectory(),SDCARD_LOG_PATH + LOGNAME);// 获取跟目录
				if (!logFile.exists()) {
					logFile.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(logFile, true);
				fos.write(message.toString().getBytes("utf-8"));
				fos.close();
			}
		} catch (Exception e) {
			Log.e("SdcardLogs:", LogStackTrace(e));
		}
	}
}
