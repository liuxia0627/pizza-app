package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Order;
import com.jiaren.pizzaapp.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(Status status);

    List<Order> findByPhoneNumber(String phoneNumber);

    List<Order> findByCreateTimeBetween(LocalDateTime beginTime, LocalDateTime endTime);

    List<Order> findByTotalPriceBetween(Double lowBound, Double highBound);

}
