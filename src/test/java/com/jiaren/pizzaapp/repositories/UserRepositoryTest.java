package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Sex;
import com.jiaren.pizzaapp.entities.User;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void before() {
        User testUser = new User();
        testUser.setName("testUser");
        userRepository.save(testUser);
    }

    @Test
    public void testAddUser() {
        User userForAdding = new User();
        userForAdding.setName("userForAdding");
        User returnedUserAfterAdding = userRepository.save(userForAdding);
        assertEquals(userForAdding.getName(), returnedUserAfterAdding.getName());
    }

    @Test
    public void testFetchUser() {
        assertEquals("testUser", userRepository.findByName("testUser").getName());
    }

    @Test
    public void testUserSex() {
        User testUser = userRepository.findByName("testUser");
        testUser.setSex(Sex.Male);
        userRepository.save(testUser);
        assertEquals(testUser.getSex(), userRepository.findByName("testUser").getSex());
    }
}