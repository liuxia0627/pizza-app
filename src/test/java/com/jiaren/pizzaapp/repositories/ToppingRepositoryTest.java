package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Topping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToppingRepositoryTest {

    @Resource
    private ToppingRepository toppingRepository;

    @Test
    public void whenSavingTopping_thenSavingShouldBeSuccessful() {
        String toppingName = "SavingTopping";
        double price = 3.0;

        Topping toppingForAdding = new Topping();
        toppingForAdding.setName(toppingName);
        toppingForAdding.setPrice(price);

        Topping returnedTopping = toppingRepository.save(toppingForAdding);

        assertEquals(toppingName, returnedTopping.getName());
        assertEquals(price, returnedTopping.getPrice());
    }

    @Test
    public void whenFindingToppingByName_thenFindingShouldBeSuccessful() {
        String toppingByName = "FindingToppingByName";
        double price = 3.0;

        Topping toppingForFetching = new Topping();
        toppingForFetching.setName(toppingByName);
        toppingForFetching.setPrice(price);

        toppingRepository.save(toppingForFetching);
        Topping fetchedTopping = toppingRepository.findByName(toppingByName);

        assertEquals(toppingByName, fetchedTopping.getName());
        assertEquals(price, fetchedTopping.getPrice());
    }

    @Test
    public void whenFindingToppingByNotExistingName_thenEntityCanNotBeFound() {
        String addedName = "FindingToppingByNotExistingName";
        String modifiedName = "ModifiedFindingToppingByNotExistingName";
        double price = 3.0;

        Topping toppingForFetching = new Topping();
        toppingForFetching.setName(addedName);
        toppingForFetching.setPrice(price);

        toppingRepository.save(toppingForFetching);
        Topping fetchedTopping = toppingRepository.findByName(modifiedName);
        assertNull(fetchedTopping);
    }

    @Test
    public void whenUpdatingTopping_thenUpdatingShouldBeSuccessful() {
        String toppingName = "UpdatingTopping";
        String modifiedName = "ModifiedUpdatingTopping";
        double price = 3.0;
        double modifiedPrice = 10.0;

        Topping toppingForFetching = new Topping();
        toppingForFetching.setName(toppingName);
        toppingForFetching.setPrice(price);

        Topping savedTopping = toppingRepository.save(toppingForFetching);
        Topping toppingInDB = toppingRepository.findById(savedTopping.getId()).orElse(null);
        assertNotNull(toppingInDB);

        toppingInDB.setName(modifiedName);
        toppingInDB.setPrice(modifiedPrice);
        Topping returnedTopping = toppingRepository.save(toppingInDB);

        assertEquals(savedTopping.getId(), returnedTopping.getId());
        assertEquals(modifiedName, returnedTopping.getName());
        assertEquals(modifiedPrice, returnedTopping.getPrice());
    }

    @Test
    public void whenDeletingTopping_thenDeletingShouldBeSuccessful() {
        String typeName = "DeletingTopping";
        double price = 3.0;

        Topping ToppingForFetching = new Topping();
        ToppingForFetching.setName(typeName);
        ToppingForFetching.setPrice(price);

        Topping returnedTopping = toppingRepository.save(ToppingForFetching);
        toppingRepository.deleteById(returnedTopping.getId());
        assertFalse(toppingRepository.findById(returnedTopping.getId()).isPresent());
    }
}
