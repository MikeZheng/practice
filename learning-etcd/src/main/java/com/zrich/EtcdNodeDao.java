package com.zrich;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.curator.utils.ZKPaths;
import org.boon.etcd.Node;
import org.boon.etcd.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.sun.media.sound.SoftTuning;

/**
 * Hello world!
 * @author ZZF
 */
public class EtcdNodeDao extends EtcdBaseDao  {

    protected static final Logger LOGGER = LoggerFactory.getLogger(EtcdNodeDao.class);

    public List<PropertyItem> findProperties(String node) {
        LOGGER.debug("Find properties in node: [{}]", node);
        List<PropertyItem> properties = Lists.newArrayList();
        try {
            Response data = getClient().list(node.substring(1));
            if (data != null && data.node() != null) {
                List<Node> childs = data.node().getNodes();

                if (childs == null) {
                    return properties;
                }

                for (Node child : childs) {
                    String key = child.key().substring(child.key().lastIndexOf("/") + 1);
                    String jsonValue = child.getValue();
                    JSONObject jsonObject = JSONObject.fromObject(jsonValue);
                    PropertyItem item = new PropertyItem(key, jsonObject.getString("value"),
                            jsonObject.getString("comment"));
                    properties.add(item);
                }
            }
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
        return properties;
    }


    public List<String> listChildren(String node) {
        LOGGER.debug("Find children of node: [{}]", node);
        List<String> children = new ArrayList();
        try {
            Response data = getClient().list(node.substring(1));
            if (data != null && data.node() != null) {
                List<Node> childs = data.node().getNodes();
                if (childs != null) {
                    for (Node child : childs) {
                        if (child.isDir()) {
                            String key = child.key().substring(child.key().lastIndexOf("/") + 1);
                            children.add(key);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
        return children;
    }

}
