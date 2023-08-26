package com.example.sealconsumer.seal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import com.alibaba.fastjson.JSONObject;

@RestController
@Slf4j
public class LoginConsumerController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private HttpHeaders headers;

    @PostMapping("/seal/user/login")
    public String userLogin(@RequestParam(value="employeeId") String employeeId,
                            @RequestParam(value="password") String password) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("seal-login-service");
        log.info("service address: "+serviceInstance.getUri());
        log.info("service name: "+ serviceInstance.getServiceId());
        log.info("this is for seal consumer user login.");
        HashMap<String, Object> map = new HashMap<>();
        map.put("employeeId", employeeId);
        map.put("password", password);
        //用HttpEntity封装整个请求报文
        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(map, headers);

        String callServiceResult = new RestTemplate().postForObject(serviceInstance.getUri().toString()+"/seal/user/login",request,String.class);

        log.info(callServiceResult);

        return callServiceResult;
    }
}
