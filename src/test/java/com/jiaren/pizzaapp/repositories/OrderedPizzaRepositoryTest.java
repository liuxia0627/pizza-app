package com.jiaren.pizzaapp.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiaren.pizzaapp.entities.OrderedPizza;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderedPizzaRepositoryTest {

    @Autowired
    private OrderedPizzaRepository orderedPizzaRepository;

    @Test
    public void testAddOrderedPizza() {
        OrderedPizza orderedPizzaForAdding = new OrderedPizza();
        Map<String, Object> infoMap = new HashMap<>();
        List<Object> infoList = new ArrayList<>();
        infoList.add(new OrderedPizza());
        infoList.add(new OrderedPizza());
        infoMap.put("pizzaInfo", new OrderedPizza());
        infoMap.put("toppingInfo", infoList);
        orderedPizzaForAdding.setInfo(infoMap);
        OrderedPizza returnedOrderedPizzaAfterAdding = orderedPizzaRepository.save(orderedPizzaForAdding);

        assertEquals(orderedPizzaForAdding.getInfo(), returnedOrderedPizzaAfterAdding.getInfo());
    }

    @Test
    public void testFetchOrderedPizzaById() throws JsonProcessingException {
        OrderedPizza orderedPizzaForFetching = new OrderedPizza();
        Map<String, Object> infoMap = new HashMap<>();
        List<Object> infoList = new ArrayList<>();
        infoList.add(new OrderedPizza());
        infoList.add(new OrderedPizza());
        infoMap.put("pizzaInfo", new OrderedPizza());
        infoMap.put("toppingInfo", infoList);
        orderedPizzaForFetching.setInfo(infoMap);
        OrderedPizza returnedOrderedPizzaAfterAdding = orderedPizzaRepository.save(orderedPizzaForFetching);

        OrderedPizza fetchedOrderedPizza = orderedPizzaRepository.findById(returnedOrderedPizzaAfterAdding.getId()).orElse(null);

        assertNotNull(fetchedOrderedPizza);
        assertEquals(orderedPizzaForFetching.getId(), fetchedOrderedPizza.getId());
        //  problem: ObjectMapper cannot include the object type in Json. This test should be refactored.
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.writeValueAsString(orderedPizzaForFetching), mapper.writeValueAsString(fetchedOrderedPizza));
    }

    @Test
    public void testDeleteOrderedPizzaById() {
        OrderedPizza orderedPizzaForDeleting = new OrderedPizza();
        Map<String, Object> infoMap = new HashMap<>();
        List<Object> infoList = new ArrayList<>();
        infoList.add(new OrderedPizza());
        infoList.add(new OrderedPizza());
        infoMap.put("pizzaInfo", new OrderedPizza());
        infoMap.put("toppingInfo", infoList);
        orderedPizzaForDeleting.setInfo(infoMap);
        OrderedPizza returnedOrderedPizzaAfterAdding = orderedPizzaRepository.save(orderedPizzaForDeleting);

        orderedPizzaRepository.deleteById(returnedOrderedPizzaAfterAdding.getId());

        assertFalse(orderedPizzaRepository.findById(returnedOrderedPizzaAfterAdding.getId()).isPresent());
    }

    @Test
    public void testUpdateOrderedPizzaById() throws JsonProcessingException {
        OrderedPizza orderedPizzaForAdding = new OrderedPizza();
        Map<String, Object> infoMapForAdding = new HashMap<>();
        List<Object> infoListForAdding = new ArrayList<>();
        infoListForAdding.add(new OrderedPizza());
        infoListForAdding.add(new OrderedPizza());
        infoMapForAdding.put("pizzaInfo", new OrderedPizza());
        infoMapForAdding.put("toppingInfo", infoListForAdding);
        orderedPizzaForAdding.setInfo(infoMapForAdding);

        OrderedPizza returnedOrderedPizzaAfterAdding = orderedPizzaRepository.save(orderedPizzaForAdding);
        OrderedPizza fetchedOrderedPizzaForUpdating = orderedPizzaRepository.findById(returnedOrderedPizzaAfterAdding.getId()).orElse(null);
        assertNotNull(fetchedOrderedPizzaForUpdating);

        Map<String, Object> infoMapForUpdating = new HashMap<>();
        List<Object> infoListForUpdating = new ArrayList<>();
        infoListForUpdating.add(new OrderedPizza());
        infoMapForUpdating.put("pizzaInfo", new OrderedPizza());
        infoMapForUpdating.put("toppingInfo1", infoListForUpdating);
        fetchedOrderedPizzaForUpdating.setInfo(infoMapForUpdating);

        OrderedPizza returnedOrderedPizzaAfterUpdating = orderedPizzaRepository.save(fetchedOrderedPizzaForUpdating);
        OrderedPizza fetchedOrderedPizzaAfterUpdating = orderedPizzaRepository.findById(returnedOrderedPizzaAfterAdding.getId()).orElse(null);

        assertNotNull(fetchedOrderedPizzaAfterUpdating);
        assertEquals(orderedPizzaForAdding.getId(), fetchedOrderedPizzaAfterUpdating.getId());
        //  problem: ObjectMapper cannot include the object type in Json. This test should be refactored.
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.writeValueAsString(returnedOrderedPizzaAfterUpdating), mapper.writeValueAsString(fetchedOrderedPizzaAfterUpdating));
    }

}
