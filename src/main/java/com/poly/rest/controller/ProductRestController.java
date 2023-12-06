package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.been.Product;
import com.poly.service.OrderDetailService;
import com.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("rest/products")
public class ProductRestController {
	@Autowired
	ProductService productService;
	@Autowired
	OrderDetailService orderDetailService;
	
	// lấy id
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}
	
	// load sản phẩm trong js sp 
	@GetMapping()
	public List<Product> getAll() {
		return productService.findAll();
	}
	@PostMapping()
	public Product create(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
//	@PostMapping()
//	public ResponseEntity<Product> create(@RequestBody Product product) {
//	    try {
//	        Product createdProduct = productService.saveProduct(product);
//	        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//	    } catch (Exception e) {
//	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
	
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
		return productService.update(product);
	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		orderDetailService.deleteByProductId(id);
		productService.deleteProduct(id);
	}

}
