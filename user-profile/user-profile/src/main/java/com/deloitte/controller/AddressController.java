package com.deloitte.controller;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deloitte.bo.Address;
import com.deloitte.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddressController {
	
	private final AddressRepository addressRepository;
   
	public  AddressController(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
    }
	
    @GetMapping(path = "/internal/address/{orderId}")
    public Address getProducts(@PathVariable long orderId ) {
        return addressRepository.getAddress(orderId);
    }
}
