package com.landptf.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;


/** 
* @ClassName: SharePreferencesM 
* @Description: SharedPreferences帮助类
* @author landptf
* @date 2015-8-27 下午11:41:24  
*/ 
public class SharePreferencesM {

	/**
	 * * 写入配置文件
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void put(Context context, String fileName, String key, Object object) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		if (object instanceof String) {
			editor.putString(key, (String) object);
		} else if (object instanceof Integer) {
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean) {
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float) {
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long) {
			editor.putLong(key, (Long) object);
		} else {
			editor.putString(key, object.toString());
		}
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * * 获得配置文件某个Key的值
	 * 
	 * @param context
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object get(Context context, String fileName, String key, Object defaultObject) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		if (defaultObject instanceof String) {
			return sp.getString(key, (String) defaultObject);
		} else if (defaultObject instanceof Integer) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if (defaultObject instanceof Boolean) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if (defaultObject instanceof Float) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if (defaultObject instanceof Long) {
			return sp.getLong(key, (Long) defaultObject);
		}
		return null;
	}

	/**
	 * *移除某个节点
	 * 
	 * @param context
	 * @param key
	 */
	public static void remove(Context context, String fileName, String key) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(key);
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * * 清除全部节点
	 * 
	 * @param context
	 */
	public static void clear(Context context, String fileName) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * * 判断配置文件里是否包含某个Key
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean contains(Context context, String key, String fileName) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sp.contains(key);
	}

	/**
	 * * 获取全部节点
	 * 
	 * @param context
	 * @return
	 */
	public static Map<String, ?> getAll(Context context, String fileName) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sp.getAll();
	}

	private static class SharedPreferencesCompat {
		private static final Method sApplyMethod = findApplyMethod();

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static Method findApplyMethod() {
			try {
				Class clz = SharedPreferences.Editor.class;
				return clz.getMethod("apply");
			} catch (NoSuchMethodException e) {
			}
			return null;
		}

		public static void apply(SharedPreferences.Editor editor) {
			try {
				if (sApplyMethod != null) {
					sApplyMethod.invoke(editor);
					return;
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
			editor.commit();
		}
	}
}
