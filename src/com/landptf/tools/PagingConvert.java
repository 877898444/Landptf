package com.landptf.tools;


import org.json.JSONException;
import org.json.JSONObject;

import com.landptf.tools.JsonM;

public class PagingConvert {
	/**
	 * 解析分页实体
	 * @param json
	 * @param t
	 * @return
	 * @throws JSONException
	 */
	public static <T> Paging toPagingData(String json, Class<T> t) throws JSONException{
		return toPagingData(json, t, false);
	}
	/**
	 * 解析分页实体
	 * @param json
	 * @param t
	 * @return
	 * @throws JSONException
	 */
	public static <T> Paging toPagingData(String json, Class<T> t,Boolean classParentFlag) throws JSONException{
		Paging pd = new Paging();
		JSONObject jo = new JSONObject(json);
		if (jo != null) {
			pd = JsonM.parseObject(json, Paging.class);
		}
		pd.setList(JsonM.parseArray(jo.getString("list"), t, classParentFlag));
		return pd;
	}
}
