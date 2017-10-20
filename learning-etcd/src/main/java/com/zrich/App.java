package com.zrich;

import java.util.Map;

/**
 * Hello world!
 * @author ZZF
 */
public class App {
    public static void main(String[] args) {

        String appRoot = "/mopon/test/youban";
        String project = "/thrift";
        String version = "3.0";

        EtcdService etcdService = new EtcdService();

        Map<String, String> itemList = etcdService.getVersionPropertyItem(appRoot, project, version);
        etcdService.prettyPrintPropertyItemList(itemList);

    }
}
