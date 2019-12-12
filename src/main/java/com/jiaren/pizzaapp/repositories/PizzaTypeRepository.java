package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.PizzaType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaTypeRepository extends JpaRepository<PizzaType, Long> {
    PizzaType findByName(String name);
}
