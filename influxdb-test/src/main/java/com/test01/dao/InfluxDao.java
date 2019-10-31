package com.test01.dao;

import org.influxdb.dto.QueryResult;

import java.util.Map;

public interface InfluxDao {

    //测试连接是否正常
    Boolean ping();

     //创建数据库
    void createDataBase(String... dataBaseName);

    //删除数据库
    void deleteDataBase(String... dataBaseName);

    //插入poin数据
    void insert(String measurement, Map<String, String> tags, Map<String, Object> fields);

    //查询poin数据
    public QueryResult query(String command);

    //时序数据库不支持删除和修改poin

}
