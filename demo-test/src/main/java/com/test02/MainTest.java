package com.test02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.test02"})
public class MainTest {
    public static void main(String[] args){
        SpringApplication application = new SpringApplication(MainTest.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
    @Bean
    public Dog dog01() {
        return new Dog();
    }
}