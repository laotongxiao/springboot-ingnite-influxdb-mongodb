package com.test01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.test01"})
public class MainTest {
    public static void main(String[] args){
        SpringApplication application = new SpringApplication(MainTest.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}