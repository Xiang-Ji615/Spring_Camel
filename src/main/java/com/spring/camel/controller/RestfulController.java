package com.spring.camel.controller;

import com.spring.camel.service.TestFilter;
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

    @GetMapping("Add")
    public String AddFilter(){
        testFilter.setFileNames(new HashSet<>(Arrays.asList("pom.xml", "Application.java")));
        return "true";
    }
}
