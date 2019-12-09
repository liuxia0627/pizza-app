package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Role;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testAddRole() {
        Role roleForAdding = new Role();
        roleForAdding.setName("test1");
        Role returnedRoleAfterAdding = roleRepository.save(roleForAdding);
        assertEquals(roleForAdding.getName(), returnedRoleAfterAdding.getName());
    }

    @Test
    public void testFetchRole() {
        Role roleForFetching = new Role();
        roleForFetching.setName("test2");
        roleRepository.save(roleForFetching);
        assertEquals("test2", roleRepository.findByName("test2").getName());
    }
}