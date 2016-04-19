package com.landptf.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

public class ImageM {
	private static final String TAG = "ImageM";
	
	public static final int PNG = 0;
	public static final int JPG = 1;
	
	/**
	 * 加载本地图片
	 * @param url
	 * @return
	 */
	public static Bitmap getLocalBitmap(String url) {
		Bitmap bitmap = null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(url);
			bitmap = BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 从服务器取图片
	 * @param url
	 * @return
	 */
	public static Bitmap getHttpBitmap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setConnectTimeout(0);
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	
	/**
	 * save the bitmap to local
	 * @param bitmap
	 * @param filePath
	 * @param fileName
	 * @param format
	 */
	public static void saveLocalImage(Bitmap bitmap, String filePath, String fileName, int format){
		try {
			String sdStatus = Environment.getExternalStorageState();  
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
				Log.i(TAG, "SD card is not avaiable/writeable right now.");
				return;
			}

			File tmpFile = new File(filePath);
			if (!tmpFile.exists()) {
				if (!tmpFile.mkdirs()) {
					return;
				}
			}
			File file = new File(filePath + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			switch (format) {
			case PNG:
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);//png类型
				break;
			case JPG:
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//jpg类型
				break;
			default:
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);//png类型
				break;
			}
			FileOutputStream out= new FileOutputStream(file);
	        out.write(baos.toByteArray());
	        out.flush();
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 将图片转成Base64格式
     * @param imgPath
     * @return
     */
    public static String img2Base64(String imgPath) {  
        if (!FileM.IsExists(imgPath)) {
			return null;
		}
        Bitmap bitmap = getSmallBitmap(imgPath);
        ByteArrayOutputStream out = null;  
        try {  
            out = new ByteArrayOutputStream();  
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);  
            out.flush();  
            out.close();  
            byte[] imgBytes = out.toByteArray();  
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);  
        } catch (Exception e) {  
            return null;  
        } finally {  
            try {  
                out.flush();  
                out.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    /**
	 * 根据路径获得图片并压缩返回bitmap用于显示
	 * 
	 * @param imagesrc
	 * @return
	 */
	public static Bitmap getSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inJustDecodeBounds = false;
		options.inSampleSize = calculateInSampleSize(options, 480, 800);
		return BitmapFactory.decodeFile(filePath, options);
	}
	
	/**
	 * 计算图片的缩放值
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
}
