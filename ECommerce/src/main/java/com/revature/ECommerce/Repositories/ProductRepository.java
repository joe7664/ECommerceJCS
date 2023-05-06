package com.revature.ECommerce.Repositories;

import com.revature.ECommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByProductId(Long id);

    Product findProductByProductName(String productName);
}
