package com.zrich;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.curator.utils.ZKPaths;

/**
 * <p>Description: </p>
 * @author 郑振富
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2017</p>
 */
public class EtcdService {

    private EtcdNodeDao etcdNodeDao = new EtcdNodeDao();
    private EtcdPropertyDao propertyDao = new EtcdPropertyDao();



    public List<String> getVersion(String appRoot, String project) {
        String projectRoot = ZKPaths.makePath(appRoot, project);
        List<String> versionList = etcdNodeDao.listChildren(projectRoot);
        return versionList;
    }

    public List<String> getProject(String appRoot) {
        return etcdNodeDao.listChildren(appRoot);
    }

    public List<String> getGroup(String appRoot, String project, String version) {
        String projectRoot = ZKPaths.makePath(appRoot, project);
        String versionRoot = ZKPaths.makePath(projectRoot, version);

        EtcdNodeDao etcdNodeDao = new EtcdNodeDao();
        return etcdNodeDao.listChildren(versionRoot);
    }

    public Map<String, String> getVersionPropertyItem(String appRoot, String project, String version) {
        String projectRoot = ZKPaths.makePath(appRoot, project);
        String versionRoot = ZKPaths.makePath(projectRoot, version);
        List<String> groupList = getGroup(appRoot,project,version);

        Map<String, String> map = new TreeMap<>();

        for (String group : groupList) {
            String groupRoot = ZKPaths.makePath(versionRoot, group);
            List<PropertyItem> propertyItems = etcdNodeDao.findProperties(groupRoot);
            for (PropertyItem propertyItem : propertyItems) {
                map.put(ZKPaths.makePath(group, propertyItem.getName()), propertyItem.getValue() +"; comment= "+ propertyItem.getComment());
            }
        }
        return map;
    }

    public void prettyPrintPropertyItemList(Map<String, String> itemMap) {
        itemMap.forEach((a, b) -> System.out.println(a + "   =     " + b));
    }

    public void compare(String appRoot, String project, String version1, String version2) {
        Map<String, String> version1Map = getVersionPropertyItem(appRoot, project, version1);
        Map<String, String> version2Map = getVersionPropertyItem(appRoot, project, version2);

        for (String key : version1Map.keySet()) {
            String value = version1Map.get(key);
            String value2 = version2Map.get(key);

            if (value2 == null || !value.equals(value2)) {
                System.out.println(version1 + " : " + key + " = " + value);
                System.out.println(version2 + " : " + key + " = " + value2);
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

    public void copyVersion(String appRoot, String project, String oldVersion, String newVersion) {
        String projectRoot = ZKPaths.makePath(appRoot, project);
        String oldVersionRoot = ZKPaths.makePath(projectRoot, oldVersion);
        String newVersionRoot = ZKPaths.makePath(projectRoot, newVersion);

        propertyDao.createGroup(newVersionRoot);

        List<String> groupList = etcdNodeDao.listChildren(oldVersionRoot);
        String itemRoot;
        String oldGroupRoot;
        String newGroupRoot;
        for (String group : groupList) {

            oldGroupRoot = ZKPaths.makePath(oldVersionRoot, group);
            newGroupRoot = ZKPaths.makePath(newVersionRoot, group);

            propertyDao.createGroup(newGroupRoot);

            List<PropertyItem> propertyItems = etcdNodeDao.findProperties(oldGroupRoot);
            for (PropertyItem propertyItem : propertyItems) {

                itemRoot = ZKPaths.makePath(newGroupRoot, propertyItem.getName());

                System.out.println("Start Copy " + itemRoot + " = " + propertyItem.getValue());
                propertyDao.createProperty(itemRoot, propertyItem.getValue(), propertyItem.getComment());
                System.out.println("End Copy " + itemRoot + " = " + propertyItem.getValue());
            }
        }
    }

}
