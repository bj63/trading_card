package org.example.cards.database.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.example.cards.database.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderDAO extends JpaRepository<Order, Integer> {

        // Query to get order details including card information
        @Query(value = "SELECT o.id AS order_id, " +
                "o.order_date, " +
                "od.quantity_ordered AS quantity, " +
                "c.player_name AS card_name, " +  // Correct column name
                "c.id AS card_id, " +
                "c.buy_price AS price, " +       // Correct column name
                "(od.quantity_ordered * c.buy_price) AS total " +  // Correct column name
                "FROM orderdetails od " +
                "JOIN cards c ON od.card_id = c.id " +  // Ensure this is the correct join column
                "JOIN orders o ON o.id = od.order_id " +
                "WHERE o.id = :orderId", nativeQuery = true)
        List<Map<String, Object>> getOrderDetails(Integer orderId);

        // Query to get the total order amount for a given order
        @Query(value = "SELECT SUM(od.quantity_ordered * c.buy_price) AS orderTotal " +  // Correct column name
                "FROM orderdetails od " +
                "JOIN cards c ON od.card_id = c.id " +  // Ensure this is the correct join column
                "JOIN orders o ON o.id = od.order_id " +
                "WHERE o.id = :orderId", nativeQuery = true)
        Double getOrderTotal(Integer orderId);

        // Query to find an order in CART status for a given user
        @Query(value = "SELECT * FROM orders " +
                "WHERE user_id = :userId AND status = 'CART'", nativeQuery = true)
        Order findOrderInCartStatus(Integer userId);
    }
