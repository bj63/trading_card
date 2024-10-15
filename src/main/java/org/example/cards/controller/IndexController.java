package org.example.cards.controller;
import lombok.extern.slf4j.Slf4j;
import org.example.cards.database.dao.CardDAO;
import org.example.cards.database.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private CardDAO cardDAO;


    // Home page
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView response = new ModelAndView("index");
        List<Card> cards = cardDAO.findAll(); // Fetch all cards
        response.addObject("cards", cards);
        return response;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView response = new ModelAndView("login");

        return response;
    }


}