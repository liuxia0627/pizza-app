package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
    Topping findByName(String name);
}
