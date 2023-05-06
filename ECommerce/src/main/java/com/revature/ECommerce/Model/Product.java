package com.revature.ECommerce.Model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column
    private String productName;
    @Column
    private String description;
    @Column
    private double price;

//    @ManyToOne(fetch = FetchType.EAGER)
//    //@JsonManagedReference
//    Account account;
}
