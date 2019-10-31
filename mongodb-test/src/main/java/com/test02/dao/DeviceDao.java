package com.test02.dao;

import com.test02.projo.Device;

import java.util.List;

public interface DeviceDao {
    //新增
    public void saveDevice(Device device);

    //删除
    public void removeDevice(String id);

    //修改
    public void updateDevice(Device device);

    //根据编号查询
    public  Device findById(String id);

    //根据用户ID查询
    public  Device findByUserId(Object userId);

    //查询所有
    public List<Device> findAll();
}
