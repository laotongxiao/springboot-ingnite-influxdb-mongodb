package com.test02.dao.impl;

import com.test02.dao.UserDao;
import com.test02.projo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    //新增
    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    //删除
    @Override
    public void removeUser(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, User.class);
    }

    //修改
    @Override
    public void updateUser(User user) {
        Query query = new Query(Criteria.where("_id").is(user.getId()));
        Update update = new Update();
        update.set("name", user.getName());
        mongoTemplate.updateFirst(query,update, User.class);
    }

    //根据编号查询
    @Override
    public User findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    //查询所有
    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }
}
