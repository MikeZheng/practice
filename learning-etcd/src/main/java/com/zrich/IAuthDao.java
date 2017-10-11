package com.zrich;

public interface IAuthDao {

	/**
	 * 检查授权
	 * 
	 * @param nodeName
	 * @param password
	 * @return
	 */
	boolean checkAuth(String nodeName, String password);

	/**
	 * 授权
	 * 
	 * @param nodeName
	 * @param password
	 * @return true: 授权成功; false: 节点已被授权
	 */
	boolean auth(String nodeName, String password);

	/**
	 * 检查节点名是否存在
	 * 
	 * @param nodeName
	 * @return true: 不存在; false: 存在
	 */
	boolean checkNodeName(String nodeName);
}
