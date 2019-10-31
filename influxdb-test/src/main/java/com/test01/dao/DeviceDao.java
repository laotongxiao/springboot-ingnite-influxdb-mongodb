package com.test01.dao;

import org.influxdb.dto.QueryResult;

import java.util.Map;

public interface DeviceDao {
    //插入poin数据
    void insert(String measurement, Map<String, String> tags, Map<String, Object> fields);

    //查询poin数据
    public QueryResult query(String command);
}
