package com.example.sealconsumer.seal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("user")
public class LoginConsumerController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @PostMapping("/login")
    public String userLogin(@RequestParam(value="employeeId") String employeeId,
                            @RequestParam(value="password") String password) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("seal-login-service");
        log.info("service address: "+serviceInstance.getUri());
        log.info("service name: "+ serviceInstance.getServiceId());
        log.info("this is for seal consumer user login.");
        Map<String, String> params = new HashMap<>();
        params.put("employeeId", employeeId);
        params.put("password", password);
        String stringPath="/seal/user/login?employeeId="+employeeId+"&"+"password="+password;
        String requestUrl=serviceInstance.getUri().toString()+stringPath;
        log.info("request url: "+serviceInstance.getUri().toString()+stringPath);

        String callServiceResult = new RestTemplate().postForObject(requestUrl,null,String.class);

        log.info(callServiceResult);

        return callServiceResult;
    }
}
