package org.example.cards.database.dao;
import org.example.cards.database.entity.TradedCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradedCardDAO extends JpaRepository<TradedCard, Integer> {

    // Find all traded cards for a specific user
    List<TradedCard> findByUserId(Integer userId);

    // Find a traded card by its ID
    TradedCard findByTradeId(Integer tradeId);

    // Count the number of traded cards for a specific user
    int countByUserId(Integer userId);

    // Find all traded cards with associated card and user
    @Query("select tc from TradedCard tc join fetch tc.card join fetch tc.user")
    List<TradedCard> findAllTradedCards();
}