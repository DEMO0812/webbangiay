package com.poly.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.been.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
	List<OrderDetail> findByProductId(Integer productId);

	void deleteByProductId(Integer productId);

// thống kê
//	 @Query("SELECT SUM(od.quantity) FROM OrderDetail od WHERE od.order.createDate > :startDate")
//	    long calculateTotalQuantity(@Param("startDate") java.util.Date startDate);
//	 

}
