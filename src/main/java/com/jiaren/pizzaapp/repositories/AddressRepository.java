package com.jiaren.pizzaapp.repositories;

import com.jiaren.pizzaapp.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByAddressDetails(String addressDetails);

    List<Address> findByAddressDetailsContainsIgnoreCase(String addressContains);

}
