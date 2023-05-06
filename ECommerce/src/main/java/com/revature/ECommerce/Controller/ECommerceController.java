package com.revature.ECommerce.Controller;

import com.revature.ECommerce.Exception.InvalidCredential;
import com.revature.ECommerce.Exception.UserNotFoundException;
import com.revature.ECommerce.Model.Product;
import com.revature.ECommerce.Model.Account;
import com.revature.ECommerce.Service.ECommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
//@RequestMapping("")
public class ECommerceController {
    ECommerceService eCommerceService;

    @Autowired
    public ECommerceController(ECommerceService eCommerceService){
        this.eCommerceService = eCommerceService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> postUser(@RequestBody Account account){
        try{
            eCommerceService.createAccount(account);
            return ResponseEntity.ok("User successfully registered");
        }catch(InvalidCredential e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){
        try{
            Account login = eCommerceService.login(account.getEmail(), account.getPassword());
            return ResponseEntity.ok(login);
        }catch(UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> postProduct(@RequestBody Product product){
        Product existing = eCommerceService.getProductByName(product.getProductName());
        if(existing==null){
            eCommerceService.createProduct(product);
            return ResponseEntity.ok(product);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product with that name already exists");
        }
    }

    @GetMapping("/home")
    public List<Product> getProducts(){
        return eCommerceService.getAllProducts();
    }

    @GetMapping("/product/{productId}")
    public Product getProductById(@PathVariable long productId){
        return eCommerceService.getProductById(productId);
    }

    @GetMapping("/cart/{accountId}")
    public List<Product> getCart(@PathVariable long accountId){
        return eCommerceService.getCart(accountId);
    }

    @PostMapping("/cart/{accountId}/{productId}")
    public ResponseEntity<?> postProductToCart(@PathVariable long accountId, @PathVariable long productId){
        eCommerceService.addToCart(accountId, productId);
        return ResponseEntity.ok("Item added to cart");
    }

    @DeleteMapping("/cart/{accountId}")
    public ResponseEntity<?> deleteCart(@PathVariable long accountId){
        eCommerceService.deleteCart(accountId);
        return ResponseEntity.ok("Cart cleared");
    }
}
