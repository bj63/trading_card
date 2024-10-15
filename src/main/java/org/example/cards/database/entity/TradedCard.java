package org.example.cards.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "traded_cards")
public class TradedCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Integer tradeId;

    @Column(name = "user_id", insertable = false, updatable = false, nullable = false)
    private Integer userId;

    @Column(name = "card_id", insertable = false, updatable = false)
    private Integer cardId;

    @Column(name = "trade_date")
    @Temporal(TemporalType.DATE)
    private Date tradeDate;

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
}

