package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Customer;

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
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void before() {
        Customer testCustomer = new Customer();
        testCustomer.setName("testCustomer");
        customerRepository.save(testCustomer);
    }

    @Test
    public void testAddCustomer() {
        Customer customerForAdding = new Customer();
        customerForAdding.setName("customerForAdding");
        Customer returnedCustomerAfterAdding = customerRepository.save(customerForAdding);
        assertEquals(customerForAdding.getName(), returnedCustomerAfterAdding.getName());
    }

    @Test
    public void testFetchUser() {
        assertEquals("testCustomer", customerRepository.findByName("testCustomer").getName());
    }
}