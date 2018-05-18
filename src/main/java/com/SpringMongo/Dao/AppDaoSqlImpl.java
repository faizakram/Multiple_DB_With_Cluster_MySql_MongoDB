package com.SpringMongo.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.SpringMongo.Model.User;

@Repository
public class AppDaoSqlImpl extends AbstractSqlDao<Integer, User> implements AppDaoSql {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public User getUserDetails(Integer id) {
		return getByKey(id);
	}
	
}
