package com.SpringMongo.Dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.SpringMongo.CommonConstant.CommonConstants;

public abstract class AbstractMongoDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractMongoDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
    public T findByDPK(PK value) {
        Query query = new Query(Criteria.where(CommonConstants.ID).is(value));
        return mongoTemplate.findOne(query, persistentClass);
    }
    
    public T findByAnyKey(String key,PK value) {
        Query query = new Query(Criteria.where(key).is(value));
        return mongoTemplate.findOne(query, persistentClass);
    }
    
    public void create(T entity) {
        mongoTemplate.insert(entity);
    }
    public void update(T entity) {
        mongoTemplate.save(entity);
    }
 
    public void delete(T entity) {
        mongoTemplate.remove(entity);
    }
 
    public void deleteAll() {
        mongoTemplate.remove(new Query(), persistentClass);
    }

    public List <T> findAll() {
        return mongoTemplate.findAll(persistentClass);
    }
}
