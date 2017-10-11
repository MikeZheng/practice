package com.zrich;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.boon.etcd.ClientBuilder;
import org.boon.etcd.Etcd;

/**
 * <p>Description: </p>
 * @author 郑振富
 * @version 1.0
 *          <p>Company:Mopon</p>
 *          <p>Copyright:Copyright(c)2017</p>
 */
public class EtcdBaseDao {
    private ClientBuilder builder = null;

    private Etcd client = null;

    private String etcdAddress= "http://172.16.30.20:2379";

    public void setEtcdAddress(String etcdAddress) {
        this.etcdAddress = etcdAddress;
    }

    public String getEtcdAddress() {
        return etcdAddress;
    }

    public EtcdBaseDao() {
        init();
    }


    public void init() {
        builder = new ClientBuilder();

        String[] etcdUrl = etcdAddress.split(",");
        URI uriArray[] = new URI[etcdUrl.length];
        for (int i = 0; i < etcdUrl.length; i++) {
            uriArray[i] = URI.create(etcdUrl[i]);
        }

        builder.hosts(uriArray);
        builder.poolSize(1);
        builder.timeOutInMilliseconds(30 * 1000);

        client = builder.createClient();

    }

    @PreDestroy
    private void destory() {
    }

    protected Etcd getClient() {
        return client;
    }
}
