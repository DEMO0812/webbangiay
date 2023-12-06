package com.poly.service;

import java.util.Date;
import java.util.Map;


public interface ThongKeService {
	  public Date calculateStartDate(int days);
	  public Map<String, Object> getOrderStatistics(Date startDate, Date endDate);
}
