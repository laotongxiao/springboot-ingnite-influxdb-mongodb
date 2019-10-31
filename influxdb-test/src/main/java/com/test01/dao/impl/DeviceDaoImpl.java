package com.test01.dao.impl;

import com.test01.config.InfluxDbUtils;
import com.test01.dao.DeviceDao;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Point.Builder;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class DeviceDaoImpl implements DeviceDao {
    @Autowired
    private InfluxDbUtils influxDbUtils;
    //插入poin数据
    @Override
    public void insert(String measurement, Map<String, String> tags, Map<String, Object> fields){
        InfluxDB influxDB = influxDbUtils.getInfluxDB();
        String databaseName = influxDbUtils.getDatabase();
        Builder builder = Point.measurement(measurement);
        builder.tag(tags);
        builder.fields(fields);
        influxDB.write(databaseName, "", builder.build());
    }
    //查询poin数据
    @Override
    public QueryResult query(String command) {
        InfluxDB influxDB = influxDbUtils.getInfluxDB();
        String databaseName = influxDbUtils.getDatabase();
        Query query = new Query(command, databaseName);
        return influxDB.query(query);
    }
}
