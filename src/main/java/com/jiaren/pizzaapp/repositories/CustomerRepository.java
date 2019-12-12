package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
}
