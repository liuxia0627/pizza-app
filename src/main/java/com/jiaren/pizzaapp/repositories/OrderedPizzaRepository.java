package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.OrderedPizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedPizzaRepository extends JpaRepository<OrderedPizza, Long> {

}
