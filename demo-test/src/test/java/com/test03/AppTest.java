package com.test03;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainTest.class)
public class AppTest {
    @Autowired
    public Cat catObject01;
    @Test
    public void test01(){
        System.out.println(catObject01.getId());
        System.out.println(catObject01.getName());
    }
}
