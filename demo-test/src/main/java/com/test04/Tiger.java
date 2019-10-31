package com.test04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Tiger {
    @Value("${spring.demo.tiger.id}")
    private String id;
    @Value("${spring.demo.tiger.name:bigTiger}")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
