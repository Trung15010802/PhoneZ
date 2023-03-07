package com.gdu.nhom1.shopproject.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "product_id", referencedColumnName = "id")
    // private Product product;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private List<Product> products;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String firstName;
    private String lastName;
    private String address;
    private String town_city;
    private long phoneNumber;
    private String email;
    private String addInformation;
    private double price;

}
