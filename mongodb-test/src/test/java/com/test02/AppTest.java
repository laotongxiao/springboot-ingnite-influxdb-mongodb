package com.test02;

import com.test02.dao.DeviceDao;
import com.test02.dao.UserAndDeviceDao;
import com.test02.dao.UserDao;
import com.test02.projo.Device;
import com.test02.projo.User;
import org.bson.types.ObjectId;
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
    private UserDao userDao;
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private UserAndDeviceDao userAndDeviceDao;
    @Test
    public void test01(){
        User user = userDao.findById("5dae7adba305a5cb23f1fc13");
        //System.out.println((Object) user.getId());
        //Device device2 = deviceDao.findById("5dae8d0ca305a5cb23f1fc14");
        //UserId对像变字符串型
        //String str = device2.getUserId().toString();
        //System.out.println(str);
        //device2设置UserId属性
        //device2.setUserId(new ObjectId("5dae7adba305a5cb23f1fc13"));
        //修改以应device
        //deviceDao.updateDevice(device2);

       Device device = deviceDao.findByUserId(new ObjectId("5dae7adba305a5cb23f1fc13"));
       System.out.println(device.getName());
//        System.out.println(device.getUserId());

        //System.out.println(new ObjectId(user.getId()));
        //device.setUserId();
        //System.out.println(device.getUserId());
        //deviceDao.updateDevice(device);
        System.out.println("---------ok");
        List<User> all = userDao.findAll();
        for (int i=0; i<all.size(); i++){
            User object = (User) all.get(i);
            System.out.println(object.getId());
            System.out.println(object.getName());
        }
        Object object = userAndDeviceDao.userAndDeviceFind();
        System.out.println(object.toString());
    }
}