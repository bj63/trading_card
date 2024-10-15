package org.example.cards.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class CreateCardFormBean {

    // This will be populated when editing a card
    // It will be null if this is a create card
    private Integer cardId;

    @NotEmpty(message = "Card number is required")
    private String cardNumber;

    @NotEmpty(message = "Player name is required")
    private String playerName;

    @NotEmpty(message = "Team name is required")
    private String teamName;

    @NotNull(message = "Price is required")
    private Double buyPrice;

    private String imageUrl;

    private Integer availableCopies;

    private MultipartFile image;

}