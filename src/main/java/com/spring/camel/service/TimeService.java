package com.spring.camel.service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeService {

    public String getTime(){
        return "Time is : " + LocalDateTime.now();
    }
}
