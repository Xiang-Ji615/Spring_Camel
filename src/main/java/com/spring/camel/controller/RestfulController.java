package com.spring.camel.controller;

import com.spring.camel.route.HelloWorldRoute;
import com.spring.camel.service.TestFilter;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

@RestController
@RequestMapping("/Rest")
public class RestfulController {

    @Autowired
    TestFilter testFilter;

    CamelContext camelContext = new DefaultCamelContext();

    @GetMapping("Add")
    public String AddFilter(){
        testFilter.setFileNames(new HashSet<>(Arrays.asList("pom.xml", "Application.java")));
        return "true";
    }

    @GetMapping("Start")
    public String Start() throws Exception {
        camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new HelloWorldRoute());
        camelContext.start();
        camelContext.getShutdownStrategy().setTimeout(2);
        return "OK";
    }

    @GetMapping("Stop")
    public String Stop() {
        camelContext.stop();
        return "Stop";
    }
}
