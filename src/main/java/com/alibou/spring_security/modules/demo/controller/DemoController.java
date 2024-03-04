package com.alibou.spring_security.modules.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
@Transactional
public class DemoController {
    @GetMapping
    public ResponseEntity<String> sayHello() {
        System.out.println("1");
        return ResponseEntity.ok("Hello endpoint");
    }
}
