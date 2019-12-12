package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.PizzaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PizzaTypeRepositoryTest {

    @Resource
    private PizzaTypeRepository pizzaTypeRepository;

    @Test
    public void whenSavingPizzaType_thenSavingShouldBeSuccessful() {
        String typeName = "SavingPizzaType";
        int size = 7;
        double price = 9.0;

        PizzaType typeForAdding = new PizzaType();
        typeForAdding.setName(typeName);
        typeForAdding.setSize(size);
        typeForAdding.setPrice(price);

        PizzaType returnedType = pizzaTypeRepository.save(typeForAdding);

        assertEquals(typeName, returnedType.getName());
        assertEquals(size, returnedType.getSize());
        assertEquals(price, returnedType.getPrice());
    }

    @Test
    public void whenFindingPizzaTypeByName_thenFindingShouldBeSuccessful() {
        String typeName = "FindingPizzaTypeByName";
        int size = 7;
        double price = 9.0;

        PizzaType typeForFetching = new PizzaType();
        typeForFetching.setName(typeName);
        typeForFetching.setSize(size);
        typeForFetching.setPrice(price);

        pizzaTypeRepository.save(typeForFetching);
        PizzaType fetchedType = pizzaTypeRepository.findByName(typeName);

        assertEquals(typeName, fetchedType.getName());
        assertEquals(size, fetchedType.getSize());
        assertEquals(price, fetchedType.getPrice());
    }

    @Test
    public void whenFindingPizzaTypeByNotExistingName_thenEntityCanNotBeFound() {
        String addedName = "FindingPizzaTypeByNotExistingName";
        String modifiedName = "ModifiedFindingPizzaTypeByNotExistingName";
        int size = 7;
        double price = 9.0;

        PizzaType typeForFetching = new PizzaType();
        typeForFetching.setName(addedName);
        typeForFetching.setSize(size);
        typeForFetching.setPrice(price);

        pizzaTypeRepository.save(typeForFetching);
        PizzaType fetchedType = pizzaTypeRepository.findByName(modifiedName);
        assertNull(fetchedType);
    }

    @Test
    public void whenUpdatingPizzaType_thenUpdatingShouldBeSuccessful() {
        String typeName = "UpdatingPizzaType";
        String modifiedName = "ModifiedUpdatingPizzaType";
        int size = 7;
        int modifiedSize = 6;
        double price = 9.0;
        double modifiedPrice = 10.0;

        PizzaType typeForFetching = new PizzaType();
        typeForFetching.setName(typeName);
        typeForFetching.setSize(size);
        typeForFetching.setPrice(price);

        PizzaType savedType = pizzaTypeRepository.save(typeForFetching);
        PizzaType pizzaTypeInDB = pizzaTypeRepository.findById(savedType.getId()).orElse(null);
        assertNotNull(pizzaTypeInDB);

        pizzaTypeInDB.setName(modifiedName);
        pizzaTypeInDB.setSize(modifiedSize);
        pizzaTypeInDB.setPrice(modifiedPrice);
        PizzaType returnedType = pizzaTypeRepository.save(pizzaTypeInDB);

        assertEquals(savedType.getId(), returnedType.getId());
        assertEquals(modifiedName, returnedType.getName());
        assertEquals(modifiedSize, returnedType.getSize());
        assertEquals(modifiedPrice, returnedType.getPrice());
    }

    @Test
    public void whenDeletingPizzaType_thenDeletingShouldBeSuccessful() {
        String typeName = "DeletingPizzaType";
        int size = 7;
        double price = 9.0;

        PizzaType typeForDeleting = new PizzaType();
        typeForDeleting.setName(typeName);
        typeForDeleting.setSize(size);
        typeForDeleting.setPrice(price);

        PizzaType returnedType = pizzaTypeRepository.save(typeForDeleting);
        pizzaTypeRepository.deleteById(returnedType.getId());
        assertFalse(pizzaTypeRepository.findById(returnedType.getId()).isPresent());
    }
}
