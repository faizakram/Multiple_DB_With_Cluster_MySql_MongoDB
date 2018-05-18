package com.SpringMongo.Dao;

import org.springframework.stereotype.Repository;

import com.SpringMongo.Model.Car;

@Repository
public class AppDaoMongoImpl extends AbstractMongoDao<String, Car> implements AppDaoMongo {

	@Override
	public void update(Car car) {
		create(car);
	}

	/**
	 * This method will call any key of collections
	 */
	@Override
	public Car findByKey(String keyName, String value) {
		return findByAnyKey(keyName, value);
	}

	/***
	 * This method will call with default primary key.
	 * 
	 * @param keyValue
	 * @return
	 */
	public Car findByKey(String keyValue) {
		return findByDPK(keyValue);
	}

}
