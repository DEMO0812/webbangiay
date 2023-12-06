package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.been.OrderDetail;
import com.poly.service.OrderDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("rest/orderDetails")
public class OrderDetailRestController {

	@Autowired
	OrderDetailService orderDetailService;

	// tìm id trong product
	@GetMapping()
	public List<OrderDetail> getOrderDetailsByProductId(@RequestParam Integer productId) {
		return orderDetailService.getOrderDetailsByProductId(productId);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		orderDetailService.deleteOrderDetail(id);
	}
	
	@DeleteMapping("/product/{productId}")
    public void deleteByProductId(@PathVariable Integer productId) {
        orderDetailService.deleteByProductId(productId);
    }
}
