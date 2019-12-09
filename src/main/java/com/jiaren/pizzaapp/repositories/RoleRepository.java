package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
