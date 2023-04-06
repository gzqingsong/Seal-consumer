package com.example.sealconsumer.seal.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Log
public class SealConsumerController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("/seal/consumer")
    public String equals() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("seal-service");
        log.info("service address: "+serviceInstance.getUri());
        log.info("service name: "+ serviceInstance.getServiceId());
        log.info("port: 8083");
        log.info("this is test for seal consumer.");
        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString()+"/seal",String.class);
        log.info(callServiceResult);

        return callServiceResult;
    }
}
