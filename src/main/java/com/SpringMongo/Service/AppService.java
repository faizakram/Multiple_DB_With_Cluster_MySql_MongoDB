package com.SpringMongo.Service;

import com.SpringMongo.Entity.JsonResponse;

public interface AppService {

	public JsonResponse getUserDetails(Integer id);

	public JsonResponse getCarDetails(String param1, String param2);

}
