package com.drinkshop.repository;

import com.drinkshop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    User findByUsername(String username);

    User findById(int id);

    User save(User user);
}
