package com.zrich;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 报文都是Json格式
 * <p>Description: </p>
 * @date 2016年7月7日
 * @author 周顺得
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public abstract class JsonFormatPackets {


	public ValueFilter getFilter() {
		return filter;
	}

	public void setFilter(ValueFilter filter) {
		this.filter = filter;
	}

	/**
	 * 过滤器
	 */
	private ValueFilter filter = new ValueFilter() {
		public Object process(Object obj, String s, Object v) {
			if (v == null)
				return "";
			return v;
		}
	};

	public String toJSON() {
		return JSON.toJSONString(this, filter);
	}
}
