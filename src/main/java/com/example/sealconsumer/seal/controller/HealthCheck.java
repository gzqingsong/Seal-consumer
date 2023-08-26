package com.example.sealconsumer.seal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/seal/health")
    public String health(){
        return "start: UP";
    }
}
