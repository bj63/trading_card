package org.example.cards.database.dao;

import org.example.cards.database.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailsDAO extends JpaRepository<OrderDetail, Integer> {

    @Query(value = "select * from orderdetails where order_id = :orderId and card_id = :cardId", nativeQuery = true)
    OrderDetail isCardInCart(Integer orderId, Integer cardId);


    // Update method to use the correct field name 'cardId'
    void deleteByCard_CardId(Integer cardId);
}