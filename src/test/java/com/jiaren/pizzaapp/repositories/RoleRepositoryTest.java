package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Role;
import com.jiaren.pizzaapp.entities.User;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;

    @Before
    public void before(){
        Role testRole = new Role();
        testRole.setName("testRole");
        roleRepository.save(testRole);
        User testUser = new User();
        testUser.setName("testUser");
        testUser.setRoleSet(new HashSet<Role>());
        testUser.getRoleSet().add(testRole);
        userRepository.save(testUser);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testAddRole() {
        Role roleForAdding = new Role();
        roleForAdding.setName("testAddRole");
        Role returnedRoleAfterAdding = roleRepository.save(roleForAdding);
        assertEquals(roleForAdding.getName(), returnedRoleAfterAdding.getName());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testFetchRoleByName() {
        assertEquals("testRole", roleRepository.findByName("testRole").getName());
    }

//    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    public void testFetchRoleByUserId() {
//        User testUser = userRepository.findByName("testUser");
//        Long userId = testUser.getId();
//        assertEquals(testUser.getRoleSet(), roleRepository.findByUser_Id(userId));
//    }

//    @Test
//    public void testFetchRoleByUser() {
//        User testUser = userRepository.findByName("testUser");
//        assertEquals(testUser.getRoleSet(), roleRepository.findByUser(testUser));
//    }
}