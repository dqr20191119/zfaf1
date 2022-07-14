package com.cesgroup.prison.alarm.dateD.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AlarmDataService {
	void loadAlertorData(String cusNumber, String dvcType, String areaId);

 	void loadPlanData(HttpServletRequest request, HttpServletResponse response);

}
