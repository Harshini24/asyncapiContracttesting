package com.deloitte;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.deloitte.bo.Address;
import com.deloitte.controller.AddressController;
import com.deloitte.repository.AddressRepository;

@SpringBootTest(classes = UserProfile.class)
@RunWith(SpringRunner.class)

public class UserprofileBase {

    @Autowired
    private AddressController addressController;
    
    @MockBean
    private AddressRepository addressRepository;
    
    @Before
    public void before() {
    	System.out.println("IM here *********"); 	
    	Long orderId = (long) 4561;
		Mockito.when((this.addressRepository.getAddress(orderId))).thenReturn(new Address("Deloitte","Bangalore",637002));
        RestAssuredMockMvc.standaloneSetup(this.addressController);
    }
    
    

}
