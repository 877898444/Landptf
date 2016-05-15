package com.landptf.tools;

import android.annotation.SuppressLint;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * List对象工具类
 * @author landptf
 * @date 2015-5-26
 */
public class ListM {
	/**
	 * 判断对象是否为空
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T> boolean isNull(T t){
		return t==null;
	}
	/**
	 * 判断数组对象是否为空且没有数据
	 * @param <T>
	 * @param T[] ts
	 * @return
	 */
	public static <T> boolean isNullOrNoData(T[] ts){
		return isNull(ts) || ts.length==0;
	}
	
	/**
	 * 判断集合对象是否为空且没有数据
	 * @param <T>
	 * @param List<T> ts
	 * @return
	 */
	public static <T> boolean isNullOrNoData(List<T> ts){
		return isNull(ts) || ts.size()==0;
	}
	
	/**
	 * 找到满足条件的对象
	 * @param <T>
	 * @param list
	 * @param param
	 * @return
	 */
	public static <T> Object find(List<T> list, FieldInfo param){
		return find(list,param, false);
	}
	/**
	 * 找到满足条件的对象
	 * @param <T>
	 * @param list
	 * @param param
	 * @return
	 */
	public static <T> Object find(List<T> list, FieldInfo param, boolean like){
		List<FieldInfo> params = new ArrayList<FieldInfo>();
		params.add(param);
		return find(list,params, false, like);
	}
	/**
	 * 找到满足条件的对象
	 * @param <T>
	 * @param list
	 * @param params
	 * @return
	 */
	public static <T> Object find(List<T> list, List<FieldInfo> params){
		return find(list, params, false);
	}
	/**
	 * 找到满足条件的对象
	 * @param <T>
	 * @param list 目标list
	 * @param params 条件集合
	 * @param or 条件之间的关联  or为false 时 是 AND关系 ;  为true时是 OR关系
	 * @return
	 */
	public static <T> Object find(List<T> list, List<FieldInfo> params,boolean or){
		return find(list,params,or,false);
	}
	/**
	 * 找到满足条件的对象
	 * @param <T>
	 * @param list 目标list
	 * @param params 条件集合
	 * @param or 条件之间的关联  or为false 时 是 AND关系 ;  为true时是 OR关系
	 * @return
	 */
	public static <T> Object find(List<T> list, List<FieldInfo> params,boolean or, Boolean like){
		if(isNullOrNoData(params)){
			return null;
		}
		boolean find = false;
		for (T t : list) {
			find = false;
			for (FieldInfo fInfo : params) {
				if (like) {
					find = getFieldValueByName(t, fInfo.getName()).toString().contains(fInfo.getValue().toString());
				}else {
					find = getFieldValueByName(t, fInfo.getName()).equals(fInfo.getValue());
				}
				if(or){
					if(find){
						break;
					}
				}else{
					if(!find){
						break;
					}
				}
			}
			if (find) {
				return t;
			}
		}
		return null;
	}
	/**
	 * 找到满足条件的集合 
	 * @param <T>
	 * @param list
	 * @param params
	 * @param size 返回的记录数  默认是0：取全部 大于0则返回指定的记录数
	 * @return
	 */
	public static <T> List<T> findAll(List<T> list, FieldInfo param,int size){
		List<FieldInfo> params = new ArrayList<FieldInfo>();
		params.add(param);
		return findAll(list,params, false, size);
	}
	
	/**
	 * 找到满足条件的集合 
	 * @param <T>
	 * @param list
	 * @param params
     * @param size 返回的记录数  默认是0：取全部 大于0则返回指定的记录数
	 * @return
	 */
	public static <T> List<T> findAll(List<T> list, List<FieldInfo> params,int size){
		return findAll(list, params, false,size);
	}
	
	/**
	 * 找到满足条件的集合 
	 * @param <T>
	 * @param list 目标list
	 * @param params 条件集合
	 * @param or 条件之间的关联  or为false 时 是 AND关系 ;  为true时是 OR关系
	 * @param size 返回的记录数  默认是0：取全部 大于0则返回指定的记录数
	 * @return
	 */
	public static <T> List<T> findAll(List<T> list, List<FieldInfo> params,boolean or,int size){
		List<T> listTemp = new ArrayList<T>();
		if(isNullOrNoData(params)){
			return list;
		}
		boolean find = false;
		for (T t : list) {
			if(size>0 && listTemp.size()==size){
				break;
			}
			find = false;
			for (FieldInfo fInfo : params) {
				find = getFieldValueByName(t, fInfo.getName()).equals(fInfo.getValue());
				if(or){
					if(find){
						break;
					}
				}else{
					if(!find){
						break;
					}
				}
			}
			if (find) {
				listTemp.add(t);
			}
		}
		return listTemp;
	}
	
	public static List<FieldInfo> FieldInfo(Object o){
		Field[] fields = o.getClass().getDeclaredFields();
		List<FieldInfo> list = new ArrayList<FieldInfo>();
		FieldInfo info = null;
		Field field=null;
		Object value = null;
		for (int i = 0; i < fields.length; i++) {
			field = fields[i];
			value = getFieldValueByName(o, field.getName());
			if(value==null) continue;
			info = new FieldInfo();
			info.setType(field.getType());
			info.setName(field.getName());
			info.setValue(value);
		}
		return list;
	}
	
	@SuppressLint("DefaultLocale")
	public static Object getFieldValueByName(Object o,String filedName){
		try {
			String getter = "get"+filedName.substring(0, 1).toUpperCase()+ filedName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[]{});
			Object value = method.invoke(o,new Object[]{});
			return value;
		} catch (Exception e) {
		}
		return null;
	}  
}
