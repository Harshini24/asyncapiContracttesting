package com.deloitte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.deloitte.bo.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
	
	@Query(
			  value = "SELECT * FROM products p WHERE u.order_id :orderId", 
			  nativeQuery = true)
	List<Product> getProducts(long orderId);
}
