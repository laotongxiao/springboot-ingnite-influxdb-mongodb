package com.test02.dao.impl;

import com.test02.dao.DeviceDao;
import com.test02.projo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DeviceDaoImpl implements DeviceDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    //新增
    @Override
    public void saveDevice(Device device) {
        mongoTemplate.save(device);
    }

    //删除
    @Override
    public void removeDevice(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Device.class);
    }

    //修改
    @Override
    public void updateDevice(Device device) {
        Query query = new Query(Criteria.where("_id").is(device.getId()));
        Update update = new Update();
        update.set("deviceId", device.getDeviceId());
        update.set("name", device.getName());
        update.set("userId", device.getUserId());
        mongoTemplate.updateFirst(query, update, Device.class);
    }

    //根据编号查询
    @Override
    public Device findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Device device = mongoTemplate.findOne(query, Device.class);
        return device;
    }

    //根据用户id查询
    @Override
    public Device findByUserId(Object userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        Device device = mongoTemplate.findOne(query, Device.class);
        return device;
    }

    //查询所有
    @Override
    public List<Device> findAll() {
        return mongoTemplate.findAll(Device.class);
    }
}
