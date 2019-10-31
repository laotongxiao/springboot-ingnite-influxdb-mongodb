package com.test01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainTest.class)
public class AppTest {
    @Autowired
    public UserRepository userObject01;
    @Test
    public void test01(){
        List<User> all = userObject01.findAll();
        for (int i=0; i<all.size(); i++){
            User object = (User) all.get(i);
            System.out.println(object.getId());
            System.out.println(object.getName());
        }
    }
}