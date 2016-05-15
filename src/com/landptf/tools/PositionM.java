package com.landptf.tools;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

/**
 * 获取地理位置工具类
 * @author landptf
 * @date 2015-5-26
 */
public class PositionM {
	
	public static String cityName ;  //城市名    
	private static Geocoder geocoder;   //此对象能通过经纬度来获取相应的城市等信息    
	/**  
     * 通过地理坐标获取城市名  其中CN分别是city和name的首字母缩写  
     * @param context  
     */    
    public static String getCityName(Context context){    
        geocoder = new Geocoder(context);    
        //用于获取Location对象，以及其他    
        LocationManager locationManager;     
        String serviceName = Context.LOCATION_SERVICE;    
        //实例化一个LocationManager对象    
        locationManager = (LocationManager)context.getSystemService(serviceName);    
        //provider的类型    
        String provider = LocationManager.NETWORK_PROVIDER;
    
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);   //高精度    
        criteria.setAltitudeRequired(false);    //不要求海拔    
        criteria.setBearingRequired(false); //不要求方位    
        criteria.setCostAllowed(false); //不允许有话费    
        criteria.setPowerRequirement(Criteria.POWER_LOW);   //低功耗
        //通过最后一次的地理位置来获得Location对象    
        Location location = locationManager.getLastKnownLocation(provider);
        String queryed_name = updateWithNewLocation(location);
        if((queryed_name != null) && (0 != queryed_name.length())){
            cityName = queryed_name;
        }
        return cityName;
    }
    /**  
     * 更新location  
     * @param location  
     * @return cityName  
     */    
    private static String updateWithNewLocation(Location location) {    
        String mcityName = "";    
        double lat = 0;    
        double lng = 0;    
        List<Address> addList = null;    
        if (location != null) {    
            lat = location.getLatitude();    
            lng = location.getLongitude();    
        } 
        try {
            addList = geocoder.getFromLocation(lat, lng, 1);    //解析经纬度    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
        if (addList != null && addList.size() > 0) {    
            for (int i = 0; i < addList.size(); i++) {    
                Address add = addList.get(i);    
                mcityName += add.getLocality();    
            }    
        }    
        if(mcityName.length()!=0){    
            return mcityName.substring(0, (mcityName.length()-1));    
        } else {    
            return mcityName;
        }
    }
}
