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

public class EtcdNodeDao extends EtcdBaseDao implements INodeDao {

    protected static final Logger LOGGER = LoggerFactory.getLogger(EtcdNodeDao.class);

    @Override
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

    public static void main(String[] args) {
        String appRoot = "/mopon/test/youban";
        String project ="/thrift";
        String version = "3.0";

        EtcdNodeDao etcdNodeDao = new EtcdNodeDao();
        Map<String, String> map = etcdNodeDao.getVersionPropertyItem(appRoot, project, version);
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() +" = "+entry.getValue());
        }


//        etcdNodeDao.compare(appRoot, "manager", "2.4.0", "3.2.0");
//        etcdNodeDao.compare(appRoot, "merchant", "1.0", "3.2.0");
//        etcdNodeDao.compare(appRoot, "hotel", "1.0", "3.2.0");
//        etcdNodeDao.compare(appRoot, "scenic_thrift", "1.0", "3.2.0");


//        String project  ="manager";
//        String group = "common";
//        String version = "2.4.0";
//        String projectRoot = ZKPaths.makePath(appRoot, project);
//        String versionRoot = ZKPaths.makePath(projectRoot, version);
//        String groupRoot = ZKPaths.makePath(versionRoot, group);

        /*EtcdNodeDao etcdNodeDao = new EtcdNodeDao();
        EtcdPropertyDao propertyDao = new EtcdPropertyDao();
        List<String> projectList = etcdNodeDao.listChildren(appRoot);

        for (String s : projectList) {
            String projectRoot = ZKPaths.makePath(appRoot, s);
            List<String> versions = etcdNodeDao.listChildren(projectRoot);
            for (String version : versions) {
                String versionRoot = ZKPaths.makePath(projectRoot, version);
                List<String> groupList = etcdNodeDao.listChildren(versionRoot);
                for (String group : groupList) {
                    String groupRoot = ZKPaths.makePath(versionRoot, group);
                    List<PropertyItem> propertyItems = etcdNodeDao.findProperties(groupRoot);
                    for (PropertyItem propertyItem : propertyItems) {
                        if (propertyItem.getValue().contains("172.16.10.249")) {
                            System.out.printf(s + "   " + version + "   " + group + "   " + propertyItem.getName() + " = " + propertyItem.getValue() + " comment =[" + propertyItem.getComment() + "]");
                            if (propertyItem.getValue().contains("sc_dev_170623")) {
                                System.out.println();
                                String newDBPath = propertyItem.getValue().replace("sc_dev_170623", "sc_dev_170921");
                                System.out.println(newDBPath);
//                            propertyDao.updateProperty(ZKPaths.makePath(groupRoot, propertyItem.getName()), newDBPath, propertyItem.getComment());

                            }

                            if (propertyItem.getValue().contains("mc_dev_0427")) {
                                System.out.println();
                                String newDBPath = propertyItem.getValue().replace("mc_dev_0427", "mc_dev_170921");
                                System.out.println(newDBPath);
                                //                            propertyDao.updateProperty(ZKPaths.makePath(groupRoot, propertyItem.getName()), newDBPath, propertyItem.getComment());
                            }
                        }

                    }

                }
            }
        }*/





        /*if (!Strings.isNullOrEmpty(groupRoot) ) {
            List<PropertyItem> propertyItems = etcdNodeDao.findProperties(groupRoot);
            System.out.println(propertyItems);
        }*/


    }

    public void replaceProperty(String appRoot, String project, String version, String group, String oldValue, String newValue) {

    }

    public void compare(String appRoot, String project, String version1, String version2) {
        String projectRoot = ZKPaths.makePath(appRoot, project);

        Map<String, String> version1Map = getVersionPropertyItem(appRoot, project, version1);
        Map<String, String> version2Map = getVersionPropertyItem(appRoot, project, version2);

        for (String key : version1Map.keySet()) {
            String value = version1Map.get(key);
            String value2 = version2Map.get(key);

            if (value2 == null || !value.equals(value2)) {
                System.out.println(version1 + " : "+key +" = " + value);
                System.out.println(version2 + " : "+key +" = " + value2);
                System.out.println();
            }

        }
        System.out.println();

        for (String key : version2Map.keySet()) {
            if (version1Map.get(key) == null) {
                System.out.println("Version2 add new property: " + key + " = " + version2Map.get(key));
            }
        }

    }

    private Map<String, String> getVersionPropertyItem(String appRoot, String project, String version) {
        String projectRoot = ZKPaths.makePath(appRoot, project);
        String versionRoot = ZKPaths.makePath(projectRoot, version);

        EtcdNodeDao etcdNodeDao = new EtcdNodeDao();
        List<String> groupList = etcdNodeDao.listChildren(versionRoot);

        Map<String, String> map = new HashMap<>();

        for (String group : groupList) {

            String groupRoot = ZKPaths.makePath(versionRoot, group);

            List<PropertyItem> propertyItems = etcdNodeDao.findProperties(groupRoot);
            for (PropertyItem propertyItem : propertyItems) {
                map.put(ZKPaths.makePath(group, propertyItem.getName()), propertyItem.getValue() + propertyItem.getComment());
            }
        }
        return map;
    }

    public void copyVersion(String appRoot, String project, String oldVersion, String newVersion) {
        EtcdNodeDao etcdNodeDao = new EtcdNodeDao();
        EtcdPropertyDao propertyDao = new EtcdPropertyDao();

        String projectRoot = ZKPaths.makePath(appRoot, project);
        String oldVersionRoot = ZKPaths.makePath(projectRoot, oldVersion);
        String newVersionRoot = ZKPaths.makePath(projectRoot, newVersion);

        propertyDao.createGroup(newVersionRoot);

        List<String> groupList = etcdNodeDao.listChildren(oldVersionRoot);
        for (String group : groupList) {

            String oldGroupRoot = ZKPaths.makePath(oldVersionRoot, group);
            String newGroupRoot = ZKPaths.makePath(newVersionRoot, group);

            propertyDao.createGroup(newGroupRoot);

            List<PropertyItem> propertyItems = etcdNodeDao.findProperties(oldGroupRoot);
            for (PropertyItem propertyItem : propertyItems) {

                String itemRoot = ZKPaths.makePath(newGroupRoot, propertyItem.getName());
                String value = "";
                String comment = propertyItem.getComment();
                System.out.println("Start Copy " + itemRoot + " = " + propertyItem.getValue());
                if (propertyItem.getValue().contains("172.16.10.249")) {
                    if (propertyItem.getValue().contains("sc_dev_170623")) {
                        value = propertyItem.getValue().replace("sc_dev_170623", "sc_dev_170921");

                    }
                    if (propertyItem.getValue().contains("mc_dev_0427")) {
                        value = propertyItem.getValue().replace("mc_dev_0427", "mc_dev_170921");
                    }
                } else {
                    value = propertyItem.getValue();
                }
                propertyDao.createProperty(itemRoot, value, comment);
                System.out.println("End Copy " + itemRoot + " = " + value);
            }
        }
    }


}
