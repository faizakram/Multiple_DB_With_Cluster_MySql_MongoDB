package com.SpringMongo.Dao;

import com.SpringMongo.Model.Car;

public interface AppDaoMongo {

	void update(Car car);

	public Car findByKey(String key, String id);


}
