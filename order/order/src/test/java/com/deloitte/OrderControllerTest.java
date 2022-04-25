package com.deloitte;

import com.deloitte.bo.Address;
import com.deloitte.bo.OrderDetails;
import com.deloitte.bo.Product;
//import com.deloitte.repository.AddressRepository;
import com.deloitte.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.deloitte:user-profile:+:stubs:8081")
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    private ProductRepository productRepository;

    private ObjectMapper objectMappper = new ObjectMapper();

    @Test
    public void validate_get_products() throws Exception {
    	long orderId =4561;
    	List<Product> p = new ArrayList<>();
    	p.add(new Product(1,"Product1",354));
    	p.add(new Product(2,"Product2",674));
    	
		Mockito.when(this.productRepository.getProducts(orderId)).thenReturn(p);

		ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/order/4561").accept(MediaType.APPLICATION_JSON));
		MvcResult mvcResult = resultActions.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		OrderDetails order = objectMappper.readValue(response, OrderDetails.class);
		System.out.println("testing ****"+ response);
    	
		Assert.assertNotEquals(null, order.getAddress().getCustomerName());
    }
 
}

