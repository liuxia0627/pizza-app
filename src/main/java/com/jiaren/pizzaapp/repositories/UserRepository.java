package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByName(String name);
}
