package com.revature.ECommerce.Service;

import com.revature.ECommerce.Exception.InvalidCredential;
import com.revature.ECommerce.Exception.UserNotFoundException;
import com.revature.ECommerce.Model.Account;
import com.revature.ECommerce.Model.Product;
import com.revature.ECommerce.Model.Account;
import com.revature.ECommerce.Repositories.ProductRepository;
import com.revature.ECommerce.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ECommerceService {
    private AccountRepository accountRepository;
    private ProductRepository productRepository;
    @Autowired
    public ECommerceService(AccountRepository accountRepository, ProductRepository productRepository){
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        Account existingAccount = accountRepository.findAccountByEmail(account.getEmail());
        if(existingAccount != null){
            throw new InvalidCredential("User with that email already exists.");
        }
        if(account.getPassword().length() < 6){
            throw new InvalidCredential("Password must be at least 6 characters long and must contain letters");
        }
        return accountRepository.save(account);
    }

    public Account login(String email, String password) {
        Account account = accountRepository.findAccountByEmailAndPassword(email, password);
        if(account==null){
            throw new UserNotFoundException("User with that email does not exist");
        }
        return account;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String productName) {
        return productRepository.findProductByProductName(productName);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> addToCart(long accountId, long productId) {
        Account account = accountRepository.findAccountByAccountId(accountId);
        Product product = productRepository.findProductByProductId(productId);
        if(product!=null){
            account.getProducts().add(product);
        }
        return account.getProducts();
    }

    public void deleteCart(long accountId) {
        Account account = accountRepository.findAccountByAccountId(accountId);
        account.setProducts(new ArrayList<Product>());
    }
}
