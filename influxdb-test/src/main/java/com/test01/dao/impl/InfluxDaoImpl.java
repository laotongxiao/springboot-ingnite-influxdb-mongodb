package com.test01.dao.impl;

import com.test01.config.InfluxDbUtils;
import com.test01.dao.InfluxDao;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.Point.Builder;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class InfluxDaoImpl implements InfluxDao {

    @Autowired
    private InfluxDbUtils influxDbUtils;
    Logger log = LoggerFactory.getLogger(InfluxDaoImpl.class);

    //测试连接是否正常
    @Override
    public Boolean ping() {
        boolean isConnected = false;
        InfluxDB influxDB = influxDbUtils.getInfluxDB();
        Pong pong;
        try {
            pong = influxDB.ping();
            if (pong != null) {
                isConnected = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnected;
    }
    //创建数据库
    @Override
    public void createDataBase(String... dataBaseName) {
        InfluxDB influxDB = influxDbUtils.getInfluxDB();
        if (dataBaseName.length > 0) {
            //CREATE DATABASE 表示纯inclufx cmd命令
            influxDB.query(new Query("CREATE DATABASE " + dataBaseName[0]));
            return;
        }
        if (influxDbUtils.getDatabase() == null) {
            log.error("如参数不指定数据库名,配置文件 spring.influx.dataBaseName 必须指定");
            return;
        }
        influxDB.query(new Query("CREATE DATABASE " + influxDbUtils.getDatabase()));
    }
    //删除数据库
    @Override
    public void deleteDataBase(String... dataBaseName) {
        InfluxDB influxDB = influxDbUtils.getInfluxDB();
        if (dataBaseName.length > 0) {
            //CREATE DATABASE 表示纯inclufx cmd命令
            influxDB.query(new Query("DROP DATABASE " + dataBaseName[0]));
            return;
        }
        if (influxDbUtils.getDatabase() == null) {
            log.error("如参数不指定数据库名,配置文件 spring.influx.dataBaseName 必须指定");
            return;
        }
        influxDB.query(new Query("DROP DATABASE " + influxDbUtils.getDatabase()));
    }
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
