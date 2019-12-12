package com.jiaren.pizzaapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PizzaType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private double price;

}
