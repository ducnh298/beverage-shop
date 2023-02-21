package com.drinkshop.repository;

import com.drinkshop.model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAll();

    Optional<Order> findById(Integer id);

    Order save(Order order);

    @Modifying
    @Query(value = "UPDATE public.orders SET total = :total WHERE id = :id", nativeQuery = true)
    void updateOrderTotal(@Param("total") BigDecimal total, @Param("id") int orderId);
}
