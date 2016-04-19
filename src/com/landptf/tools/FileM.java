package com.landptf.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载工具类
 * 
 * @author landptf
 * @date 2015-5-27
 */
public class FileM {
	
	/**
	 * 从服务器下载文件，写入SDCard指定位置
	 * @param httpUrl
	 * @param filePath,结尾加“/”
	 * @param fileName,带后缀名
	 * @return
	 */
	public static File downLoadFile(String httpUrl, String filePath, String fileName) {
		File tmpFile = new File(filePath);
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		File file = new File(filePath + fileName);
		try {
			URL url = new URL(httpUrl);
			try {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				InputStream is = conn.getInputStream();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buf = new byte[256];
				conn.connect();
				double count = 0;
				if (conn.getResponseCode() < 400) {
					while (count <= 100) {
						if (is != null) {
							int numRead = is.read(buf);
							if (numRead <= 0) {
								break;
							} else {
								fos.write(buf, 0, numRead);
							}
						} else {
							break;
						}
					}
				}
				conn.disconnect();
				fos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * 判断fileName文件是否存在
	 * @param fileName
	 * @return boolean
	 */
	public static boolean IsExists(String fileName){
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
