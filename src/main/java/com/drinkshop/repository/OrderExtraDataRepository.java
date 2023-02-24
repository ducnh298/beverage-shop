package com.drinkshop.repository;

import com.drinkshop.model.OrderExtraData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderExtraDataRepository extends CrudRepository<OrderExtraData, Integer> {

    OrderExtraData findById(int id);

    OrderExtraData save(OrderExtraData orderExtraData);
}
