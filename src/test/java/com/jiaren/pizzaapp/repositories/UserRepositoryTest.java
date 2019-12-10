package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.User;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser() {
        User userForAdding = new User();
        userForAdding.setName("userForAdding");
        User returnedUserAfterAdding = userRepository.save(userForAdding);
        assertEquals(userForAdding.getName(), returnedUserAfterAdding.getName());
    }

    @Test
    public void testFetchUser() {
        User userForFetching = new User();
        userForFetching.setName("userForFetching");
        userRepository.save(userForFetching);
        assertEquals("userForFetching", userRepository.findByName("userForFetching").getName());
    }
}