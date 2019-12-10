package com.jiaren.pizzaapp.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@RequiredArgsConstructor
public class Customer extends BaseIdentity{
    @OneToOne
    private Address defaultAddress;

    @OneToMany
    private Set<Address> addressSet;

    @ManyToMany
    private Set<PizzaType> favoritePizzaSet;

    @OneToMany
    private Set<Order> orderSet;
}
