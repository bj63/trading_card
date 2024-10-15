package org.example.cards.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.cards.database.dao.CardDAO;
import org.example.cards.database.dao.OrderDetailsDAO;
import org.example.cards.database.entity.Card;
import org.example.cards.form.CreateCardFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Slf4j
@Service
@Component
public class CardService {

    @Autowired
    private CardDAO cardDao;

    @Autowired
    private OrderDetailsDAO orderDetailsDao;

    @Transactional
    public void deleteCardById(Integer cardId) {
        orderDetailsDao.deleteByCard_CardId(cardId);
        cardDao.deleteByCardId(cardId);
    }

    public Card createCard(CreateCardFormBean form) {
        Card card = new Card();
        card.setCardNumber(form.getCardNumber());
        card.setPlayerName(form.getPlayerName());
        card.setTeamName(form.getTeamName());
        card.setBuyPrice(form.getBuyPrice());
        card.setAvailableCopies(form.getAvailableCopies());

        String saveFilename = "./src/main/webapp/pub/image/" + form.getImage().getOriginalFilename();
        try {
            Files.copy(form.getImage().getInputStream(), Paths.get(saveFilename), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error("Unable to save image file", e);
            throw new RuntimeException("Failed to save image file", e);
        }

        String url = "/pub/image/" + form.getImage().getOriginalFilename();
        card.setImageUrl(url);

        cardDao.save(card);
        return card;
    }
}


