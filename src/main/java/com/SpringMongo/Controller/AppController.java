package com.SpringMongo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SpringMongo.CommonConstant.CommonConstants;
import com.SpringMongo.Entity.JsonResponse;
import com.SpringMongo.Service.AppService;



@RestController
@RequestMapping(value = CommonConstants.START)
public class AppController {

	@Autowired
	private AppService appService;

	@RequestMapping(value = CommonConstants.USER_DETAILS , method = RequestMethod.GET)
	public JsonResponse newUser(@PathVariable(CommonConstants.Id) Integer Id) {
		return appService.getUserDetails(Id);
	}
	@RequestMapping(value = CommonConstants.CAR_DETAILS , method = RequestMethod.GET)
	public JsonResponse newUser(@PathVariable(CommonConstants.PARA1) String param1, 
			@PathVariable(CommonConstants.PARA2) String param2) {
		return appService.getCarDetails(param1, param2);
	}


}