package com.landptf.tools;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * ������ظ�����
 * @author landptf
 */
public class NetM {
	
	/**
	 * û������
	 */
	public static final int NETWORT_NONE = 0;
	/**
	 * 2G����
	 */
	public static final int NETWORT_2G = 1;
	/**
	 * 3G����
	 */
	public static final int NETWORT_3G = 2;
	/**
	 * Wifi����
	 */
	public static final int NETWORT_WIFI = 3;
	
	private NetM(){  
        /** cannot be instantiated **/ 
        throw new UnsupportedOperationException("cannot be instantiated");  
    }  
 
    /** 
     * �ж������Ƿ�l�� 
     *  
     * @param context 
     * @return 
     */ 
    public static boolean isConnected(Context context) {  
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        if (null != connectivity){  
            NetworkInfo info = connectivity.getActiveNetworkInfo();  
            if (null != info && info.isConnected()){  
                if (info.getState() == NetworkInfo.State.CONNECTED){  
                    return true;  
                }  
            }  
        }  
        return false;  
    }  
 
    /** 
     * �ж��Ƿ���wifil�� 
     */ 
    public static boolean isWifi(Context context){  
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        if (cm == null)  
            return false;  
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;  
    }  
    
    /**
     * �жϵ�ǰ��������
     * @param context
     * @return
     */
    public static int getNetType(Context context){
    	int networkType = 0;
    	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        if (cm == null){
        	return NETWORT_NONE;
        }
        if (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI) {
        	networkType = NETWORT_WIFI;
		}else if(cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_MOBILE){
			if (isFastMobileNetwork(context)) {
				networkType = NETWORT_3G;
			}else {
				networkType = NETWORT_2G;
			}
		}else {
			networkType = NETWORT_NONE;
		}
        return networkType;
    }
 
    /** 
     * ���������ý��� 
     */ 
    public static void openSetting(Activity activity){  
        Intent intent = new Intent("/");  
        ComponentName cm = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");  
        intent.setComponent(cm);  
        intent.setAction("android.intent.action.VIEW");  
        activity.startActivityForResult(intent, 0);  
    }  
    
    
    private static boolean isFastMobileNetwork(Context context) {  
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);  
        switch (telephonyManager.getNetworkType()) {  
        case TelephonyManager.NETWORK_TYPE_1xRTT:
            return false; // ~ 50-100 kbps  
        case TelephonyManager.NETWORK_TYPE_CDMA:  
            return false; // ~ 14-64 kbps
        case TelephonyManager.NETWORK_TYPE_EDGE:  
            return false; // ~ 50-100 kbps
        case TelephonyManager.NETWORK_TYPE_EVDO_0:  
            return true; // ~ 400-1000 kbps  
        case TelephonyManager.NETWORK_TYPE_EVDO_A:  
            return true; // ~ 600-1400 kbps  
        case TelephonyManager.NETWORK_TYPE_GPRS:  
            return false; // ~ 100 kbps
        case TelephonyManager.NETWORK_TYPE_HSDPA:  
            return true; // ~ 2-14 Mbps
        case TelephonyManager.NETWORK_TYPE_HSPA:  
            return true; // ~ 700-1700 kbps  
        case TelephonyManager.NETWORK_TYPE_HSUPA:  
            return true; // ~ 1-23 Mbps  
        case TelephonyManager.NETWORK_TYPE_UMTS:  
            return true; // ~ 400-7000 kbps
        case TelephonyManager.NETWORK_TYPE_EHRPD:  
            return true; // ~ 1-2 Mbps  
        case TelephonyManager.NETWORK_TYPE_EVDO_B:  
            return true; // ~ 5 Mbps  
        case TelephonyManager.NETWORK_TYPE_HSPAP:  
            return true; // ~ 10-20
        case TelephonyManager.NETWORK_TYPE_IDEN:  
            return false; // ~25 kbps  
        case TelephonyManager.NETWORK_TYPE_LTE:  
            return true; // ~ 10+ Mbps  
        case TelephonyManager.NETWORK_TYPE_UNKNOWN:  
            return false;  
        default:  
            return false;  
  
        }  
    }  

}
