# spring-boot 的mongodb
### 说明
 1.   #### 打开库   
      use testdb      
 2.   #### 添加集合user的文档  
      db.user.insertMany([{name:"zhangsan",password:"123456"},{name:"lisi",password:"789123"}])     
 3.   #### 添加集合device的文档  
      db.device.insertOne({deviceId:"cg-4936",name:"sb01",userId:ObjectId("5dae7adba305a5cb23f1fc13")})   
 4.   ####  user基础类设计
```
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
 5.   ####  device基础类设计(重点private Object userId;)
```
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "device")
public class Device {
    @Id
    private String id;
    private String deviceId;
    private String name;
    private Object userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }
}
```
 5.   #### 扩展联表查询的参考案例  
```

@Autowired
MongoTemplate mongoTemplate;

public List<ReClassGroup> demo() {
LookupOperation lookupToLots = LookupOperation.newLookup().
from("eCostRecord").//关联表名 lots
localField("lsumpDocNumber").//关联字段
foreignField("lsumpDocNumber").//主表关联字段对应的次表字段
as("groups");//查询结果集合名 

//主表
Criteria ordercri = Criteria.where("eccDocNo").ne(null).ne("");
AggregationOperation match = Aggregation.match(ordercri);
//次表
Criteria ordercri1 = Criteria.where("groups.success").is(true);
AggregationOperation match1 = Aggregation.match(ordercri);

UnwindOperation unwind = Aggregation.unwind("groups");
Aggregation aggregation = Aggregation.newAggregation(match, match1, lookupToLots, unwind);

return mongoTemplate.aggregate(aggregation, "reClassGroup", ReClassGroup.class).getMappedResults();
```