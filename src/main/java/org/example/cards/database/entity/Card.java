package org.example.cards.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "buy_price", columnDefinition = "DECIMAL")
    private Double buyPrice;

    @Column(name = "available_copies")
    private Integer availableCopies;

    @Column(name = "image_url")
    private String imageUrl;

}
