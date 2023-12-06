package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.been.Product;
import com.poly.dao.ProductDAO;
import com.poly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDAO pDao;

	@Override
	public List<Product> findAll() {
		 return pDao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return pDao.findById(id).orElse(null);
	}

	@Override
	public Product saveProduct(Product product) {
		return pDao.save(product);
	}

	@Override
	public void deleteProduct(Integer id) {
		 pDao.deleteById(id);
		
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return pDao.findByCategoryId(cid);
	}

	@Override
	public Product update(Product product) {
		return pDao.save(product);
	}
}
