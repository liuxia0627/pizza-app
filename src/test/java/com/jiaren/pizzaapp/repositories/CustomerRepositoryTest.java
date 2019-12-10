package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Customer;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testAddCustomer() {
        Customer customerForAdding = new Customer();
        customerForAdding.setName("customerForAdding");
        Customer returnedCustomerAfterAdding = customerRepository.save(customerForAdding);
        assertEquals(customerForAdding.getName(), returnedCustomerAfterAdding.getName());
    }

    @Test
    public void testFetchUser() {
        Customer customerForFetching = new Customer();
        customerForFetching.setName("customerForFetching");
        customerRepository.save(customerForFetching);
        assertEquals("customerForFetching", customerRepository.findByName("customerForFetching").getName());
    }
}