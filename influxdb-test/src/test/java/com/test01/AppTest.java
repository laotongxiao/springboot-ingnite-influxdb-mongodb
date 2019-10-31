package com.test01;

import com.test01.config.InfluxDbUtils;
import com.test01.dao.InfluxDao;
import com.test01.projo.Device;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Series;
import org.junit.Test;
import org.influxdb.dto.QueryResult.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainTest.class)
public class AppTest {
    @Autowired
    private InfluxDbUtils influxDbUtils;
    @Autowired
    private InfluxDao influxDao;
    //连接是否正常测试
    @Test
    public void test01(){
        System.out.println(influxDao.ping());
    }
    //创建数据库,删除数据库测试
    @Test
    public void test02(){
        influxDao.createDataBase("device");
        influxDao.deleteDataBase("device");
    }
    //插入数据测试
    @Test
    public void test03() {
        InfluxDB influxDB = influxDbUtils.getInfluxDB();
        Map<String, String> tags = new HashMap<String, String>();
        Map<String, Object> fields = new HashMap<String, Object>();
        List<Device> deviceList = new ArrayList<Device>();

        Device device1 = new Device();
        device1.setDeviceId("ad0001");
        device1.setDeviceName("sb-02");
        device1.setUserId("person02");
        device1.setValue("233");
        deviceList.add(device1);

        for (Device device : deviceList) {
            //tags.put("host", "server01");
            fields.put("deviceId", device.getDeviceId());
            fields.put("deviceName", device.getDeviceName());
            fields.put("userId", device.getUserId());
            fields.put("value", device.getValue());

            influxDao.insert("device", tags, fields);

        }
        System.out.println("---ok");
    }
    //查询数据测试
    @Test
    public void test05(){
        QueryResult results = influxDao.query("select * from device");
        if(results.getResults() == null){
            return;
        }
        for (Result result : results.getResults()) {
            List<Series> series= result.getSeries();
            for (Series serie : series) {
//				Map<String, String> tags = serie.getTags();
                List<List<Object>>  values = serie.getValues();
                List<String> columns = serie.getColumns();
                getQueryData(columns, values);
            }
        }
    }
    /***整理列名、行数据***/
    private static void getQueryData(List<String> columns, List<List<Object>>  values){

        for (List<Object> list : values) {
            for(int i=0; i< list.size(); i++){
                String propertyName = columns.get(i);//字段名
                Object value = list.get(i);//相应字段值
                System.out.println(propertyName+":"+value);
            }

        }
    }

}
