package com.gdu.nhom1.shopproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity //Chỉ định class này là một entity
@Data // Tạo gettter setter cho tất cả các field (dependency lombok)
public class Category {
    @Id //Chỉ định khoá chính cho entity
    @GeneratedValue(strategy = GenerationType.AUTO) // Chỉ định cách trường id được tạo cho các giá trị của khóa chính.
    @Column(name = "category_id ")
    private int id;
    private String name;
}
