package com.zrich;

import java.util.List;

import net.sf.json.JSONObject;

import org.boon.etcd.Node;
import org.boon.etcd.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * @author ZZF
 */
public class EtcdPropertyDao extends EtcdBaseDao   {

	private static final Logger LOGGER = LoggerFactory.getLogger(EtcdPropertyDao.class);

	public boolean createProperty(String nodeName, String value, String comment) {
		LOGGER.debug("Create property : [{}] = [{}]", nodeName, value);
		boolean suc = false;
		try {
            JSONObject json = new JSONObject();
            json.put("value", value);
            json.put("comment", comment);
			Response response = getClient().set(nodeName.substring(1), json.toString());
			suc = response.successful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suc;
	}

    public boolean updateProperty(String nodeName, String value, String comment) {
		LOGGER.debug("Update property: [{}] = [{}]", nodeName, value);
		boolean suc = false;
        JSONObject json = new JSONObject();
        json.put("value", value);
        json.put("comment", comment);
		try {
			Response response = getClient().setIfExists(nodeName.substring(1), json.toString());
			suc = response.successful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suc;
	}

    public void deleteProperty(String nodeName) {
		LOGGER.debug("Delete property: [{}]", nodeName);
		try {
			getClient().delete(nodeName.substring(1));
		} catch (Exception e) {
            e.printStackTrace();
		}
	}

    public void deleteGorup(String nodeName) {
		LOGGER.debug("deleteGorup property: [{}]", nodeName);
		try {

			Response response = getClient().list(nodeName.substring(1));
			if (response != null && response.node() != null) {
				List<Node> childs = response.node().getNodes();
				if (childs != null) {
					for (Node child : childs) {
						LOGGER.debug("deleteGorup item: [{}]", child.key());
						getClient().delete(child.key().substring(1));
					}
				}
			}

			getClient().deleteDir(nodeName.substring(1));

		} catch (Exception e) {
            e.printStackTrace();
		}
	}


    public boolean createGroup(String nodeName) {
		LOGGER.debug("Create createGroup : [{}] ", nodeName);
		boolean suc = false;
		try {
			Response response = getClient().createDir(nodeName.substring(1));
			suc = response.successful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suc;
	}

}
