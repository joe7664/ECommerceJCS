package com.revature.ECommerce.Model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private double totalPrice;

    @OneToMany(fetch = FetchType.EAGER)
    //@JsonManagedReference
    List<Product> products;
}
