package com.spring.camel.route;

import com.spring.camel.service.TestFilter;
import com.spring.camel.service.TimeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

//@Component
public class HelloWorldRoute extends RouteBuilder {

    @Autowired
    TimeService timeService;

    @Autowired
    TestFilter testFilter;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void configure() throws Exception {
        from("file:in?noop=true&idempotentKey=${file:name}-${file:modified}&recursive=true")
                .threads(Thread.activeCount(), Thread.activeCount(), "myThread")
                .process(e -> {
                        String fileName = e.getIn().getBody(File.class).getName();
//                        if (new File(fileName).exists()) {
//                            //Compute alternative name
//                            e.getIn().setHeader("CamelFileName",
//                                    fileName + UUID.randomUUID().toString());
//                        } else {
//                            e.getIn().setHeader("CamelFileName", fileName);
//                        }
                        log.info("File anme : " + fileName );
                     }
                 )
                .log("Copying ${in.header.CamelFileName}")
                .to("file:out")
//                .bean(timeService)
                .log("Finished copying ${in.header.CamelFileName}");
    }
}
