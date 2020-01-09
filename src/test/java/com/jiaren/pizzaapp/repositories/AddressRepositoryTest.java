package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testAddAddress() {
        Address addressForAdding = new Address();
        addressForAdding.setAddressDetails("VICMelbourneCollinsStreet3080");
        Address returnedAddressAfterAdding = addressRepository.save(addressForAdding);
        assertEquals(addressForAdding.getAddressDetails(), returnedAddressAfterAdding.getAddressDetails());
    }

    @Test
    public void testFetchAddressById() {
        Address addressForFetching = new Address();
        addressForFetching.setAddressDetails("VICMelbourneCollinsStreet3080");
        Address returnedAddressAfterAdding = addressRepository.save(addressForFetching);
        Address returnedAddressAfterFetching = addressRepository.findById(addressForFetching.getId()).orElse(null);
        assertNotNull(returnedAddressAfterFetching);
        assertEquals(returnedAddressAfterAdding, returnedAddressAfterFetching);
    }

    @Test
    public void testUpdateAddressById() {
        Address addressForAdding = new Address();
        addressForAdding.setAddressDetails("VICMelbourneCollinsStreet3080");
        addressRepository.save(addressForAdding);
        Address returnedAddressAfterFetching = addressRepository.findById(addressForAdding.getId()).orElse(null);
        assertNotNull(returnedAddressAfterFetching);
        Address addressForUpdating = new Address();
        addressForUpdating.setId(returnedAddressAfterFetching.getId());
        addressForUpdating.setAddressDetails("VICMelbourneFranklinStreet3000");
        addressRepository.save(addressForUpdating);
        Address returnedAddressAfterUpdating = addressRepository.findById(addressForUpdating.getId()).orElse(null);
        assertNotNull(returnedAddressAfterUpdating);
        assertEquals(returnedAddressAfterUpdating, addressForUpdating);
    }

    @Test
    public void testDeleteAddressById() {
        Address addressForDeleting = new Address();
        addressForDeleting.setAddressDetails("VICMelbourneCollinsStreet3080");
        addressRepository.save(addressForDeleting);
        Address returnedAddressAfterFetching = addressRepository.findById(addressForDeleting.getId()).orElse(null);
        assertNotNull(returnedAddressAfterFetching);
        addressRepository.deleteById(addressForDeleting.getId());
        Address returnedAddressAfterDeleting = addressRepository.findById(addressForDeleting.getId()).orElse(null);
        assertNull(returnedAddressAfterDeleting);
    }


}
