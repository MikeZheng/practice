package com.zrich;

import net.sf.json.JSONObject;

import org.boon.etcd.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

/**
 * 
 * <p>Description: </p>
 * @date 2016年2月1日
 * @author 李世佳 
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class EtcdAuthDao extends EtcdBaseDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EtcdAuthDao.class);

	public boolean checkAuth(String nodeName, String password) {
		LOGGER.debug("Check checkAuth: [{}]", nodeName);
		boolean isPass = false;
		try {
			Response rootNode = getClient().get(nodeName.substring(1));
			if (rootNode != null && rootNode.node() != null && rootNode.node().isDir()) {
				rootNode = getClient().get(nodeName.substring(1) + "/login$");
				if (rootNode != null && rootNode.node() != null) {
					String pwdTemp = rootNode.node().getValue();
					JSONObject jsonObject = JSONObject.fromObject(pwdTemp);
					pwdTemp = jsonObject.getString("value");
					isPass = password.equals(pwdTemp);
				}
			}
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
		return isPass;
	}

	public boolean auth(String nodeName, String password) {
		LOGGER.debug("Check auth: [{}]", nodeName);
		boolean isPass = false;
		try {
			Response rootNode = getClient().get(nodeName.substring(1));
			if (rootNode != null && rootNode.node() != null && rootNode.node().isDir()) {
				rootNode = getClient().get(nodeName.substring(1) + "/login$");
				if (rootNode != null && rootNode.node() != null) {
					String pwdTemp = rootNode.node().getValue();
					isPass = password.equals(pwdTemp);
				}
			}
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
		return isPass;
	}

	public boolean checkNodeName(String nodeName) {
		LOGGER.debug("Check checkNodeName: [{}]", nodeName);
		boolean isPass = true;
		try {
			Response rootNode = getClient().get(nodeName.substring(1));
			if (rootNode != null && rootNode.node() != null && rootNode.node().isDir()) {
				rootNode = getClient().get(nodeName.substring(1) + "/login$");
				if (rootNode != null && rootNode.node() != null) {
					String key = rootNode.node().key();
					if(key.length() > 0) {
						isPass = false;
					}
				}
			}
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
		return isPass;
	}
	
}
