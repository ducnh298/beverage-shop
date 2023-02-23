package com.drinkshop.repository;

import com.drinkshop.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

    List<Address> findByUserId(int userId);

    Address findById(int id);

    Address save(Address address);
}
