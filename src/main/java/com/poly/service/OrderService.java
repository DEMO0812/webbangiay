package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.been.Order;

public interface OrderService {
	List<Order> findAll();

	Order findById(Long id);

	Order saveOrder(Order Order);

	void deleteOrder(Long id);
	// xử lý thêm json trong js
	Order create(JsonNode orderData);
	
	List<Order> findByUsername(String username);
}
