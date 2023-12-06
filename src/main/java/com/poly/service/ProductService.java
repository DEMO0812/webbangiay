package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.been.Product;





public interface ProductService {
	List<Product> findAll();
	Product findById(Integer id);
	Product saveProduct(Product product);
	Product update(Product product);
	void deleteProduct(Integer id);
	List<Product> findByCategoryId(String cid);

}
