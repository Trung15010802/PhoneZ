package com.gdu.nhom1.shopproject.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gdu.nhom1.shopproject.convert.StringListConverter;

import lombok.Data;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "product_name", columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> productName;
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
