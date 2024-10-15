package org.example.cards.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.cards.form.EditUserFormBean;

import org.example.cards.database.dao.CardDAO;
import org.example.cards.database.dao.TradedCardDAO;
import org.example.cards.database.dao.UserDAO;
import org.example.cards.database.entity.Card;
import org.example.cards.database.entity.TradedCard;
import org.example.cards.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private TradedCardDAO tradedCardDao;

    @Autowired
    private CardDAO cardDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/detail")
    public ModelAndView detail(Principal principal) {
        ModelAndView response = new ModelAndView("user/detail");

        User user = userDao.findByEmailIgnoreCase(principal.getName());
        if (user == null) {
            // Handle the case where no user is found
            return new ModelAndView("redirect:/error");
        }
        int tradedCardCount = tradedCardDao.countByUserId(user.getId());
        response.addObject("user", user);
        response.addObject("tradedCardCount", tradedCardCount);

        return response;
    }

    @GetMapping("/collection")
    public ModelAndView collection(Principal principal, HttpSession session) {
        ModelAndView response = new ModelAndView("user/collection");

        User user = userDao.findByEmailIgnoreCase(principal.getName());
        if (user == null) {
            // Handle the case where no user is found
            return new ModelAndView("redirect:/error");
        }

        session.setAttribute("userId", user.getId());
        log.info("Set userId in session: {}", user.getId());

        List<TradedCard> tradedCards = tradedCardDao.findByUserId(user.getId());

        List<Card> cards = new ArrayList<>();
        for (TradedCard tradedCard : tradedCards) {
            Card card = cardDao.findByCardId(tradedCard.getCardId());
            cards.add(card);
        }

        response.addObject("user", user);
        response.addObject("tradedCards", tradedCards);
        response.addObject("cards", cards);

        return response;
    }

    @PostMapping("/return")
    public String returnCard(@RequestParam Integer tradeId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userDao.findById(userId);

        TradedCard tradedCard = tradedCardDao.findByTradeId(tradeId);
        if (tradedCard != null) {
            Card card = cardDao.findByCardId(tradedCard.getCardId());
            card.setAvailableCopies(card.getAvailableCopies() + 1);
            cardDao.save(card);
            tradedCardDao.delete(tradedCard);
        }
        return "redirect:/user/collection?id=" + user.getId();
    }

    @PostMapping("/trade")
    public String tradeCard(@RequestParam(required = false) Integer cardId, Principal principal) {
        if (principal == null) {
            // Handle the case where the user is not authenticated
            return "redirect:/login";
        }

        String username = principal.getName();
        User user = userDao.findByEmailIgnoreCase(username);

        Card card = cardDao.findByCardId(cardId);
        log.info("Card found: {}", card);

        if (card != null && card.getAvailableCopies() > 0) {
            TradedCard tradedCard = new TradedCard();
            tradedCard.setUser(user);
            tradedCard.setCard(card);

            tradedCard.setTradeDate(new Date());
            tradedCard.setDueDate(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000));
            tradedCardDao.save(tradedCard);

            card.setAvailableCopies(card.getAvailableCopies() - 1);
            cardDao.save(card);
        }

        return "redirect:/user/collection?id=" + user.getId();
    }

    @GetMapping("/editUser")
    public ModelAndView editUser(Principal principal) {
        ModelAndView response = new ModelAndView("user/edit-user");

        User user = userDao.findByEmailIgnoreCase(principal.getName());

        if (user != null) {
            EditUserFormBean form = new EditUserFormBean();
            form.setId(user.getId());
            form.setEmail(user.getEmail());
            form.setName(user.getName());

            response.addObject("form", form);
        } else {
            log.warn("User with id {} not found", user.getId());
        }
        return response;
    }

    @PostMapping("/editUser")
    public ModelAndView editUserSubmit(@Valid EditUserFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView("user/edit-user");

        if (bindingResult.hasErrors()) {
            response.addObject("form", form);
            return response;
        }
        String encryptedPassword = passwordEncoder.encode(form.getPassword());
        User user = userDao.findById(form.getId());
        if (user != null) {
            user.setEmail(form.getEmail());
            user.setName(form.getName());
            user.setPassword(encryptedPassword);
            userDao.save(user);
            response.setViewName("redirect:/user/detail");  // Redirect to user detail page after successful edit
        }
        return response;
    }
}
