package com.poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.poly.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;

	@RequestMapping("order/checkout")
	public String checkout() {
		return "user/checkout_thanhtoan";
	}

	@RequestMapping("order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orderList", orderService.findByUsername(username));
		return "user/my_account";
	}

	@RequestMapping("order/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		return "user/detailOrder";
	}

	@RequestMapping("/order-statistics")
	public String showOrderStatisticsPage(Model model, @RequestParam(name = "days", defaultValue = "7") int days) {
		model.addAttribute("days", days);
		return "order-statistics"; // Trả về tên của trang HTML cần hiển thị
	}

}
