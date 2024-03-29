package com.cbt.offerservice24;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "hscode", nullable = false, length = 8)
    private String hscode;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "unit", length = 5)
    private String unit;

}