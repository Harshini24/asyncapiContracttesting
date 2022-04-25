package com.deloitte.controller;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.deloitte.bo.Address;
import com.deloitte.bo.OrderDetails;
import com.deloitte.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;

@RestController
public class OrderController {
	
	@Autowired
    RestTemplate restTemplate;

    private final ProductRepository productRepository;
    public  OrderController(ProductRepository productRepository) {
		this.productRepository = productRepository;
    }
    


    @GetMapping(path = "/order/{orderId}", produces = "application/json")
    public OrderDetails getOrderDetails(@PathVariable long orderId) {
    	HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
    	
    	OrderDetails orderDetails = new OrderDetails();
    	
    	orderDetails.setProduct(productRepository.getProducts(orderId));
    	
    	orderDetails.setAddress(restTemplate.exchange("http://localhost:8081/internal/address/"+orderId, HttpMethod.GET,entity, Address.class).getBody());
    	
    	
        return orderDetails;
    }
}
