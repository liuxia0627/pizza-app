package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Order;
import com.jiaren.pizzaapp.entities.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testAddOrder() {
        Order orderForAdding = new Order();
        orderForAdding.setPhoneNumber("0423888888");
        orderForAdding.setDeliverAddress("VICMelbourneCollinsStreet3080");
        Order returnedOrderAfterAdding = orderRepository.save(orderForAdding);
        assertEquals(orderForAdding, returnedOrderAfterAdding);
    }

    @Test
    @Transactional
    public void testFetchOrderById() {
        Order orderForFetching = new Order();
        orderForFetching.setPhoneNumber("0423888888");
        orderForFetching.setDeliverAddress("VICMelbourneCollinsStreet3080");
        orderRepository.save(orderForFetching);
        Optional<Order> returnedOrderAfterFetching = orderRepository.findById(orderForFetching.getId());
        assertTrue(returnedOrderAfterFetching.isPresent());
        assertEquals(orderForFetching, returnedOrderAfterFetching.get());
    }

    @Test
    @Transactional
    public void testUpdateOrderById() {
        Order orderForAdding = new Order();
        orderForAdding.setPhoneNumber("0423888888");
        orderForAdding.setDeliverAddress("VICMelbourneCollinsStreet3080");
        orderRepository.save(orderForAdding);
        Order returnedOrderForUpdating = orderRepository.findById(orderForAdding.getId()).orElse(null);
        assertNotNull(returnedOrderForUpdating);
        assertEquals(orderForAdding.getStatus(), returnedOrderForUpdating.getStatus());

        returnedOrderForUpdating.setStatus(Status.PROCESS);
        orderRepository.save(returnedOrderForUpdating);
        Order returnedOrderAfterUpdating = orderRepository.findById(orderForAdding.getId()).orElse(null);

        assertNotNull(returnedOrderAfterUpdating);
        assertEquals(orderForAdding.getId(), returnedOrderAfterUpdating.getId());
        assertEquals(orderForAdding.getPhoneNumber(), returnedOrderAfterUpdating.getPhoneNumber());
        assertEquals(orderForAdding.getDeliverAddress(), returnedOrderAfterUpdating.getDeliverAddress());
    }

    @Test
    @Transactional
    public void testDeleteOrderById() {
        Order orderForFetching1 = new Order();
        orderForFetching1.setPhoneNumber("0423666666");
        orderForFetching1.setDeliverAddress("VICMelbourneFranklinStreet3000");
        Order orderForFetching2 = new Order();
        orderForFetching2.setPhoneNumber("0423888888");
        orderForFetching2.setDeliverAddress("VICMelbourneCollinsStreet3080");
        orderRepository.save(orderForFetching1);
        orderRepository.save(orderForFetching2);
        assertEquals(orderForFetching1, orderRepository.findById(orderForFetching1.getId()).get());
        assertEquals(orderForFetching2, orderRepository.findById(orderForFetching2.getId()).get());
        orderRepository.deleteById(orderForFetching1.getId());
        assertFalse(orderRepository.findById(orderForFetching1.getId()).isPresent());
        assertTrue(orderRepository.findById(orderForFetching2.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testFetchOrderByStatus() {
        Order orderForFetching1 = new Order();
        orderForFetching1.setPhoneNumber("0423666666");
        orderForFetching1.setDeliverAddress("VICMelbourneFranklinStreet3000");
        Order orderForFetching2 = new Order();
        orderForFetching2.setPhoneNumber("0423888888");
        orderForFetching2.setDeliverAddress("VICMelbourneCollinsStreet3080");
        orderRepository.save(orderForFetching1);
        orderRepository.save(orderForFetching2);
        Order returnedOrderForUpdating = orderRepository.findById(orderForFetching2.getId()).orElse(null);
        assertNotNull(returnedOrderForUpdating);
        returnedOrderForUpdating.setStatus(Status.COMPLETE);
        orderRepository.save(returnedOrderForUpdating);
        List<Order> returnedOrderAfterFetching = orderRepository.findByStatus(returnedOrderForUpdating.getStatus());
        assertFalse(returnedOrderAfterFetching.contains(orderForFetching1));
        assertTrue(returnedOrderAfterFetching.contains(returnedOrderForUpdating));
    }

    @Test
    @Transactional
    public void testFetchOrderByPhoneNumber() {
        Order orderForFetching1 = new Order();
        orderForFetching1.setPhoneNumber("0423666666");
        orderForFetching1.setDeliverAddress("VICMelbourneFranklinStreet3000");
        Order orderForFetching2 = new Order();
        orderForFetching2.setPhoneNumber("0423888888");
        orderForFetching2.setDeliverAddress("VICMelbourneCollinsStreet3080");
        orderRepository.save(orderForFetching1);
        orderRepository.save(orderForFetching2);
        List<Order> returnedOrderAfterFetching = orderRepository.findByPhoneNumber(orderForFetching1.getPhoneNumber());
        assertTrue(returnedOrderAfterFetching.contains(orderForFetching1));
        assertFalse(returnedOrderAfterFetching.contains(orderForFetching2));
    }

    @Test
    @Transactional
    public void testFetchOrderByDeliverAddress() {
        Order orderForFetching1 = new Order();
        orderForFetching1.setPhoneNumber("0423666666");
        orderForFetching1.setDeliverAddress("VICMelbourneFranklinStreet3000");
        Order orderForFetching2 = new Order();
        orderForFetching2.setPhoneNumber("0423888888");
        orderForFetching2.setDeliverAddress("VICMelbourneCollinsStreet3080");
        orderRepository.save(orderForFetching1);
        orderRepository.save(orderForFetching2);
        List<Order> returnedOrderAfterFetching = orderRepository.findByDeliverAddressContainsIgnoreCase("collins");
        assertTrue(returnedOrderAfterFetching.contains(orderForFetching2));
        assertFalse(returnedOrderAfterFetching.contains(orderForFetching1));
    }

    @Test
    @Transactional
    public void testFetchOrderByCreateTimeBetween() {
        Order orderForFetching1 = new Order();
        orderForFetching1.setPhoneNumber("0423666666");
        orderForFetching1.setDeliverAddress("VICMelbourneFranklinStreet3000");
        orderRepository.save(orderForFetching1);
        LocalDateTime beginTime = LocalDateTime.now();
        Order orderForFetching2 = new Order();
        orderForFetching2.setPhoneNumber("0423888888");
        orderForFetching2.setDeliverAddress("VICMelbourneCollinsStreet3080");
        orderRepository.save(orderForFetching2);
        LocalDateTime endTime = LocalDateTime.now();
        List<Order> returnedOrderAfterFetching = orderRepository.findByCreateTimeBetween(beginTime, endTime);
        assertFalse(returnedOrderAfterFetching.contains(orderForFetching1));
        assertTrue(returnedOrderAfterFetching.contains(orderForFetching2));
    }

    @Test
    @Transactional
    public void testFetchOrderByTotalPriceBetween() {
        Order orderForFetching1 = new Order();
        orderForFetching1.setPhoneNumber("0423666666");
        orderForFetching1.setDeliverAddress("VICMelbourneFranklinStreet3000");
        orderForFetching1.setTotalPrice(10.0);
        Order orderForFetching2 = new Order();
        orderForFetching2.setPhoneNumber("0423888888");
        orderForFetching2.setDeliverAddress("VICMelbourneCollinsStreet3080");
        orderForFetching2.setTotalPrice(100.0);
        orderRepository.save(orderForFetching1);
        orderRepository.save(orderForFetching2);
        List<Order> returnedOrderAfterFetching = orderRepository.findByTotalPriceBetween(5.0, 50.0);
        assertFalse(returnedOrderAfterFetching.contains(orderForFetching2));
        assertTrue(returnedOrderAfterFetching.contains(orderForFetching1));
    }

}
