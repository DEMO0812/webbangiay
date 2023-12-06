package com.poly.service.impl;

import java.util.List;
import java.util.stream.Collectors;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.been.Order;
import com.poly.been.OrderDetail;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	// private static final Logger logger =
	// LoggerFactory.getLogger(OrderService.class);
	@Autowired
	OrderDAO oDao;

	@Autowired
	OrderDetailDAO odDao;

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return oDao.findAll();
	}

	@Override
	public Order findById(Long id) {
		return oDao.findById(id).orElse(null);
	}

	@Override
	public Order saveOrder(Order Order) {
		return oDao.save(Order);
	}

	@Override
	public void deleteOrder(Long id) {
		oDao.deleteById(id);

	}

	@Override
	@Transactional
	public Order create(JsonNode orderData) {
	
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		oDao.save(order);

		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type).stream()
				.peek(d -> d.setOrder(order)).collect(Collectors.toList());
		odDao.saveAll(details);

		return order;
	}

	@Override
	public List<Order> findByUsername(String username) {
		return oDao.findByUsername(username);
	}

}
