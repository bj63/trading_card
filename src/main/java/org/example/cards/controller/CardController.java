package org.example.cards.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.cards.database.dao.CardDAO;
import org.example.cards.database.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardDAO cardDao;




    @GetMapping("/detail/{cardId}")
    public ModelAndView detail(@PathVariable Integer cardId) {
        ModelAndView response = new ModelAndView("card/detail");

        // Fetch the card using the cardId
        Card card = cardDao.findByCardId(cardId);

        if (card == null) {
            // Redirect to an error page or handle when the card is not found
            return new ModelAndView("redirect:/error");
        }

        // Add the card to the model for the view
        response.addObject("card", card);

        return response;
    }


    @GetMapping("/search")
    public ModelAndView search() {
        ModelAndView response = new ModelAndView("card/search");
        return response;
    }

    @GetMapping
    public ModelAndView cardSearch(@RequestParam(required = false) String search, @RequestParam(required = false) String team) {
        ModelAndView response = new ModelAndView("card/search");

        List<Card> cards;

        // Trim search and team, and set to null if they're empty or just whitespace
        search = (search != null) ? search.trim() : null;
        team = (team != null && !team.equals("Or Select A Team")) ? team.trim() : null;

        if (search != null && team != null) {
            cards = cardDao.searchCardsByPlayerAndTeam(search, team);
        } else if (search != null) {
            cards = cardDao.searchCards(search);
        } else if (team != null) {
            cards = cardDao.searchByTeam(team);
        } else {
            // Neither search nor valid team is provided
            cards = new ArrayList<>(); // Avoid null pointer exceptions
        }

        // Log the cards for debugging purposes
        cards.forEach(card -> {
            log.debug("Card: {}", card.getPlayerName());
        });

        response.addObject("cards", cards);
        response.addObject("searchTerm", search);
        response.addObject("selectedTeam", team);

        return response;
    }

}

 /*   @PostMapping("/trade")
    public String tradeCard(@RequestParam Integer cardId, HttpSession session, Principal principal) {
        if (principal == null) {
            // Handle the case where the user is not authenticated
            return "redirect:/login";
        }

        Integer userId = (Integer) session.getAttribute("userId");
        User user = userDao.findById(userId);

        Card card = cardDao.findById(cardId);
        log.info("Card found: {}", card);

        if (card != null && card.getAvailableCopies() > 0) {
            TradedCard tradedCard = new TradedCard(); // Updated entity name
            tradedCard.setUser(user);
            tradedCard.setCard(card);
            tradedCard.setTradeDate(new Date()); // Updated date field
            tradedCard.setDueDate(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000)); // Optional: Adjust based on your logic
            tradedCardDao.save(tradedCard);

            card.setAvailableCopies(card.getAvailableCopies() - 1);
            cardDao.save(card);
        }

        return "redirect:/user/bookshelf?id=" + user.getId();
    }

    @PostMapping("/return")
    public String returnCard(@RequestParam Integer tradeId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userDao.findById(userId);

        TradedCard tradedCard = tradedCardDao.findById(tradeId); // Updated DAO method
        if (tradedCard != null) {
            Card card = cardDao.findById(tradedCard.getCard().getId()); // Adjusted to use Card from TradedCard
            if (card != null) {
                card.setAvailableCopies(card.getAvailableCopies() + 1);
                cardDao.save(card);
            }
            tradedCardDao.delete(tradedCard);
        }
        return "redirect:/user/bookshelf?id=" + user.getId();
    }
}*/
