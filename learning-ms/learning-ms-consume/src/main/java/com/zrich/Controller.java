package com.zrich;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 */
@RestController
public class Controller {

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/get")
    public String get() {
        List<ServiceInstance> serviceInstances= discoveryClient.getInstances("compute-service");
        if (serviceInstances != null && serviceInstances.size() > 0) {
            URI uri = serviceInstances.get(0).getUri();
            if (uri != null) {
                return new RestTemplate().getForObject(uri, String.class);
            }
        }
        return "no return";
    }

}
