package com.landptf.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Http帮助类
 * @author lyl
 */
public class HttpM {
	
	private static final int TIMEOUT_IN_MILLIONS = 5000;  
	 
    public interface CallBack {  
        void onRequestComplete(String result);  
    }  
 
    /** 
     * 异步Get请求
     *  
     * @param urlStr 
     * @param callBack 
     */ 
    public static void doGetAsyn(final String urlStr, final CallBack callBack){  
        new Thread(){  
            public void run(){  
                try{  
                    String result = doGet(urlStr);  
                    if (callBack != null){  
                        callBack.onRequestComplete(result);  
                    }  
                } catch (Exception e){  
                    e.printStackTrace();  
                }  
 
            };  
        }.start();  
    }  
 
    /** 
     * 异步Post请求
     * @param urlStr 
     * @param params 
     * @param callBack 
     * @throws Exception 
     */ 
    public static void doPostAsyn(final String urlStr, final String params,  
            final CallBack callBack) throws Exception{  
        new Thread(){  
            public void run(){  
                try{  
                    String result = doPost(urlStr, params);  
                    if (callBack != null){  
                        callBack.onRequestComplete(result);  
                    }  
                } catch (Exception e){  
                    e.printStackTrace();  
                }  
            };  
        }.start();  
    }  
 
    /** 
     * Get请求
     *  
     * @param urlStr 
     * @return 
     * @throws Exception 
     */ 
    public static String doGet(String urlStr){  
        URL url = null;  
        HttpURLConnection conn = null;  
        InputStream is = null;  
        ByteArrayOutputStream baos = null;  
        try{  
            url = new URL(urlStr);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);  
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);  
            conn.setRequestMethod("GET");  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");
            if (conn.getResponseCode() == 200){  
                is = conn.getInputStream();  
                baos = new ByteArrayOutputStream();  
                int len = -1;  
                byte[] buf = new byte[128];  
                while ((len = is.read(buf)) != -1){  
                    baos.write(buf, 0, len);  
                }  
                baos.flush();  
                return baos.toString();  
            } else{  
                throw new RuntimeException(" responseCode is not 200 ... ");  
            }  
        } catch (SocketTimeoutException e){  
            e.printStackTrace();  
        }
        catch (Exception e) {
        	e.printStackTrace();  
		}finally{  
            try{  
                if (is != null)  
                    is.close();  
            } catch (IOException e){  
            }  
            try{  
                if (baos != null)  
                    baos.close();  
            } catch (IOException e){  
            }  
            conn.disconnect();  
        }  
        return null ;  
    }  
 
    /**
     * Post请求
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, String param){  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try{  
            URL realUrl = new URL(url);  
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
            conn.setRequestProperty("charset", "utf-8");  
            conn.setUseCaches(false);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);  
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);  
            if (param != null && !param.trim().equals("")){  
                out = new PrintWriter(conn.getOutputStream());  
                out.print(param);  
                out.flush();  
            }  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null){  
                result += line;  
            }  
        } catch (Exception e){  
            e.printStackTrace();  
        }  
        finally{  
            try{  
                if (out != null){  
                    out.close();  
                }  
                if (in != null){  
                    in.close();  
                }  
            } catch (IOException ex){  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
    
    /**
     * @param url
     * @param nameValuePairs
     * @return
     */
    public static String httpClientPost(String url, ArrayList<NameValuePair> nameValuePairs){
    	String result = null;
    	HttpClient httpclient = null;
    	HttpPost httppost = null;
        try {
        	httpclient = new DefaultHttpClient();
        	httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
        	
        }finally{
        	httpclient.getConnectionManager().shutdown();
        }
        return result;
    }
}
