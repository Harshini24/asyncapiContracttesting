package com.deloitte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.deloitte.bo.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

	@Query(
			  value = "SELECT * FROM address a WHERE a.order_id :orderId", 
			  nativeQuery = true)
	Address getAddress(long orderId);
}
