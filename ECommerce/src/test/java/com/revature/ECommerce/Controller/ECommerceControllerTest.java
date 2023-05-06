package com.revature.ECommerce.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ECommerce.Model.Account;
import com.revature.ECommerce.Model.Product;
import com.revature.ECommerce.Repositories.AccountRepository;
import com.revature.ECommerce.Repositories.ProductRepository;
import com.revature.ECommerce.Service.ECommerceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.RequestEntity.post;

@SpringBootTest
@AutoConfigureMockMvc
class ECommerceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ECommerceService eCommerceService;

    public static String asJson(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Test
    void register() throws Exception {
        Account expected = new Account(1, "Joe", "joe@gmail.com", 0, new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                    .post("/register").content(asJson(expected))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        Account actual = accountRepository.findAccountByAccountId(1);
        assertEquals(expected, actual);
    }

    @Test
    void getProduct() throws Exception {
        List actual = new ArrayList<Product>();
        Product sample = new Product(1, "Apple", "Granny Smith", 0.99);//, null);
        actual.add(sample);
        sample = new Product(2, "Banana", "Fresh from Madagascar", 0.15);//, null);
        actual.add(sample);
        sample = new Product(3, "Orange", "Fresh from florida", 1.29);//, null);
        actual.add(sample);
        sample = new Product(4, "Rice", "Imported from Chine", 1.99);//, null);
        actual.add(sample);
        for(int i=0; i<actual.size(); i++) {
            mockMvc.perform(MockMvcRequestBuilders.
                    post("/product").content(asJson(actual.get(i)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        }
        Product expected = productRepository.findProductByProductId((long)2);
        assertEquals(expected, actual.get(1));
    }
}