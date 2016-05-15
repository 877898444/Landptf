package com.landptf.tools;

import java.lang.reflect.Field;

public class ResourceM {
	/**
	 * 根据名称获取classType下的文件的ID(文件名不加后缀)
	 * @param fileName
	 * @param classType
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static int getReSourceId(String fileName, Class classType){
		int id = 0;
        Field field = null;
        try {
            field = classType.getField(fileName);
            id = field.getInt(field.getName());
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return id;
	}
}
