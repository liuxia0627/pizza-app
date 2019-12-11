package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.OrderedPizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OrderedPizzaRepository extends JpaRepository<OrderedPizza, Long> {

}
