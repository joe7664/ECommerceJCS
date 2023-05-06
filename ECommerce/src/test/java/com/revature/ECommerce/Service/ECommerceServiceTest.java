package com.revature.ECommerce.Service;

import com.revature.ECommerce.Exception.ServicesException;
import com.revature.ECommerce.Model.Product;
import com.revature.ECommerce.Repositories.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ECommerceServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ECommerceService eCommerceService;
    @Autowired
    MockMvc mockMvc;


    @Test
    @DisplayName("Create product Test")
    void createProduct() throws ServicesException {
        Product product = new Product(1, "Apple", "Sample desc", 0.99);
        Product mockProduct = new Product(1, "Apple", "Sample desc", 0.99);
        when(productRepository.save(product)).thenReturn(mockProduct);
        Product result = eCommerceService.createProduct(product);
        assertEquals(mockProduct, result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    @DisplayName("Get Product by Name Test")
    void getProductByName() throws Exception {
        Product product = new Product(1, "Apple", "Sample desc", 0.99);
        Product mockProduct = new Product(1, "Apple", "Sample desc", 0.99);
        when(productRepository.save(product)).thenReturn(mockProduct);
        when(productRepository.findProductByProductName(product.getProductName())).thenReturn(mockProduct);
        Product rProduct = eCommerceService.createProduct(product);
        Product result = eCommerceService.getProductByName(rProduct.getProductName());
        assertEquals(mockProduct, result);
        verify(productRepository, times(1)).findProductByProductName(product.getProductName());
    }
}