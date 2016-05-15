/**
 * @(#)FieldInfo.java    2015-1-8
 *
 * Copyright 2015 Kingsoft Software Development Co.,Ltd, Inc. All rights reserved. 
 * Kingsoft proprietary/confidential. Use is subject to license terms.
 */
package com.landptf.tools;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 *
 * @author luolong
 * 
 * @version 2015-1-8
 * 
 * @since JDK 1.6
 * 
 */
public class FieldInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Type type=null;
	private String name ="";
	private Object value = null;
	
	public FieldInfo(){
		
	}
	
	public FieldInfo(String name,Object value){
		this.name = name;
		this.value =value;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
