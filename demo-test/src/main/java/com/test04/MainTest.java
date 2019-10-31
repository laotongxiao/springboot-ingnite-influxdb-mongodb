package com.test04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.test04"})
public class MainTest {
    public static void main(String[] args){
        SpringApplication application = new SpringApplication(MainTest.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}
