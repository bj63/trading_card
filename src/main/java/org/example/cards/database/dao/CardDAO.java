package org.example.cards.database.dao;

import org.example.cards.database.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CardDAO extends JpaRepository<Card, Long> {

    Card findByCardId(Integer cardId);


    @Query("SELECT c FROM Card c WHERE " +
            "c.cardNumber = :search OR " +
            "c.playerName LIKE CONCAT('%', :search, '%') OR " +
            "c.teamName LIKE CONCAT('%', :search, '%')")
    List<Card> searchCards(String search);

    @Query(value = "SELECT * FROM cards WHERE team_name = :search", nativeQuery = true)
    List<Card> searchByTeam(String search);

    @Query("SELECT c FROM Card c WHERE " +
            "(c.cardNumber = :search OR " +
            "c.playerName LIKE CONCAT('%', :search, '%') OR " +
            "c.teamName LIKE CONCAT('%', :search, '%')) AND " +
            "c.teamName = :team")
    List<Card> searchCardsByPlayerAndTeam(String search, String team);

    // You might want to add a method to find a card by its unique card number
    Card findByCardNumber(String cardNumber);

    void deleteByCardId(Integer cardId);

}