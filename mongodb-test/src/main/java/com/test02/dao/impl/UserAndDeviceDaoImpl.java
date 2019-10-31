package com.test02.dao.impl;


import com.test02.dao.UserAndDeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserAndDeviceDaoImpl implements UserAndDeviceDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Object userAndDeviceFind() {
        LookupOperation lookupOperation=LookupOperation.newLookup().
                from("device").  //关联从表名
                localField("_id").     //主表关联字段
                foreignField("userId").//从表关联的字段
                as("GradeAndStu");   //查询结果名
//带条件查询可以选择添加下面的条件
//       Criteria criteria=Criteria.where("studenAndgrade").not().size(0);   //只查询有结果的学生
//        Criteria qqq=Criteria.where("name").regex("文");//只查询名字中带有文的
        //       AggregationOperation match1= Aggregation.match(qqq);
//        AggregationOperation match = Aggregation.match(criteria);
//        Aggregation counts = Aggregation.newAggregation(match1,lookupOperation,match).;
        Aggregation aggregation=Aggregation.newAggregation(lookupOperation);
        List<Map> results = mongoTemplate.aggregate(aggregation,"user", Map.class).getMappedResults();
        //上面的student必须是查询的主表名
        return results;
    }
}
