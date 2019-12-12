package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Role;
import com.jiaren.pizzaapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

//    @Query("select role from Role role join role.Users user where user.id=?1")
//    List<Role> findByUser_Id(Long userId);

//    List<Role> findByUser(User user);
}
