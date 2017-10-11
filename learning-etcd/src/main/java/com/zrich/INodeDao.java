package com.zrich;

import java.util.List;

public interface INodeDao {

	/**
	 * 查找子属性
	 * 
	 * @param node
	 * @return
	 */
	List<PropertyItem> findProperties(String node);

	/**
	 * 查找子结点
	 * 
	 * @param node
	 * @return
	 */
	List<String> listChildren(String node);

}
