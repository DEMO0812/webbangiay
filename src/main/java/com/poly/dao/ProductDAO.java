package com.poly.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.been.Product;


@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
//	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);
	
	// xài dls
	 // List<Product> findByCategory_Id(String cid);
}
