package com.SpringMongo.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringMongo.Dao.AppDaoMongo;
import com.SpringMongo.Dao.AppDaoSql;
import com.SpringMongo.Entity.JsonResponse;
import com.SpringMongo.Model.Car;
import com.SpringMongo.Model.User;

@Service
public class AppServiceImpl implements AppService {

	@Autowired
	private AppDaoSql appDaoSql;
	@Autowired
	private AppDaoMongo appDaoMongo;
	
	@Override
	@Transactional
	public JsonResponse getUserDetails(Integer id) {
		User user = appDaoSql.getUserDetails(id);
		JsonResponse json = new JsonResponse();
		json.setEmail(user.getEmail());
		json.setFirstName(user.getFirstName());
		json.setLastName(user.getLastName());
		
		//Car car = new Car(user.getFirstName(), user.getLastName());
		/*Car car = appDaoMongo.findByKey(CommonConstants.ID,id);
		JsonResponse json = new JsonResponse();
		json.setEmail(car.getBrand());
		json.setFirstName(car.getModel());
		json.setLastName(car.getId());*/
		
		return json;
	}

	@Override
	public JsonResponse getCarDetails(String param1, String param2) {
		Car car = appDaoMongo.findByKey(param1,param2);
		
		JsonResponse json = new JsonResponse();
		json.setEmail(car.getBrand());
		json.setFirstName(car.getModel());
		json.setLastName(car.getId());
		
		return json;
	}

}
