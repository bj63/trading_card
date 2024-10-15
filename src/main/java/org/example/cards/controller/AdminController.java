package org.example.cards.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.cards.database.dao.CardDAO;
import org.example.cards.database.dao.UserDAO;
import org.example.cards.database.entity.Card;
import org.example.cards.database.entity.User;
import org.example.cards.form.CreateCardFormBean;
import org.example.cards.form.EditUserFormBean;
import org.example.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.nio.file.StandardCopyOption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {



    @Autowired
    private CardDAO cardDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private CardService cardService;


    // Admin dashboard to view all cards
    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView response = new ModelAndView("admin/dashboard");
        List<Card> cards = cardDao.findAll();
        response.addObject("cards", cards);
        return response;
    }
    // GET mapping to display the form to create a card
    @GetMapping("/createCard")
    public ModelAndView createCardForm() {
        ModelAndView response = new ModelAndView("admin/create-card");
        response.addObject("form", new CreateCardFormBean());
        return response;
    }

    // POST mapping to handle form submission for creating a card
    @PostMapping("/createCard")
    public ModelAndView createCardSubmit(@Valid CreateCardFormBean form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView response = new ModelAndView("admin/create-card");
            response.addObject("form", form);
            return response;
        }

        cardService.createCard(form);
        return new ModelAndView("redirect:/admin/dashboard");
    }

    // GET mapping to edit an existing card by its id
    @GetMapping("/editCard")
    public ModelAndView editCard(@RequestParam Integer cardId) {
        ModelAndView response = new ModelAndView("admin/edit-card");

        Card card = cardDao.findByCardId(cardId);

        if (card != null) {
            CreateCardFormBean form = new CreateCardFormBean();
            form.setCardId(card.getCardId());
            form.setCardNumber(card.getCardNumber());
            form.setPlayerName(card.getPlayerName());
            form.setTeamName(card.getTeamName());
            form.setBuyPrice(card.getBuyPrice());
            form.setAvailableCopies(card.getAvailableCopies());


            response.addObject("form", form);
            response.addObject("card", card);
        } else {
            log.warn("Card with id {} not found", cardId);
        }
        return response;
    }

    // POST mapping to handle form submission for editing a card
    @PostMapping("/editCard")
    public ModelAndView editCardSubmit(@Valid CreateCardFormBean form, BindingResult bindingResult, MultipartFile image) {
        ModelAndView response = new ModelAndView("admin/edit-card");

        if (bindingResult.hasErrors()) {
            response.addObject("form", form);
            return response;
        }

        Card card = cardDao.findByCardId(form.getCardId());
        if (card != null) {
            card.setCardNumber(form.getCardNumber());
            card.setPlayerName(form.getPlayerName());
            card.setTeamName(form.getTeamName());
            card.setBuyPrice(form.getBuyPrice());
            card.setAvailableCopies(form.getAvailableCopies());



            String saveFilename = "./src/main/webapp/pub/image/" + image.getOriginalFilename();
            try {
                Files.copy(image.getInputStream(), Paths.get(saveFilename), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                log.error("Unable to save image file", e);
                throw new RuntimeException("Failed to save image file", e);
            }

            String url = "/pub/image/" + image.getOriginalFilename();
            card.setImageUrl(url);

            cardDao.save(card);
            response.setViewName("redirect:/card/detail/" + card.getCardId());
        }
        return response;
    }



    @GetMapping("/cardSearch")
    public ModelAndView cardSearch(@RequestParam(required = false) String search) {
        ModelAndView response = new ModelAndView("admin/search-card");
        List<Card> cards = cardDao.searchCards(search);
        response.addObject("cards", cards);
        response.addObject("searchTerm", search);
        return response;
    }

    @PostMapping("/deleteCard")
    public ModelAndView deleteCard(@RequestParam Integer cardId) {
        try {
            cardService.deleteCardById(cardId);
        } catch (Exception e) {
            log.error("Error deleting card with id {}", cardId, e);
        }
        return new ModelAndView("redirect:/admin/dashboard");
    }

@GetMapping("/userSearch")
    public ModelAndView userSearch(@RequestParam(required = false) String search, @RequestParam(required = false) Integer searchId) {
        ModelAndView response = new ModelAndView("admin/search-user");

        List<User> users = userDao.searchUser(search, searchId);
        response.addObject("users", users);
        response.addObject("searchTerm", search);
        response.addObject("searchId", searchId);

        return response;
    }

    @GetMapping("/editUser")
    public ModelAndView editUser(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("admin/edit-user");

        User user = userDao.findById(id);

        if (user != null) {
            EditUserFormBean form = new EditUserFormBean();
            form.setId(user.getId());
            form.setEmail(user.getEmail());

            response.addObject("form", form);
        } else {
            log.warn("User with id {} not found", id);
        }
        return response;
    }

    @PostMapping("/editUser")
    public ModelAndView editUserSubmit(@Valid EditUserFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView("admin/edit-user");

        if (bindingResult.hasErrors()) {
            response.addObject("form", form);
            return response;
        }

        User user = userDao.findById(form.getId());
        if (user != null) {
            user.setEmail(form.getEmail());
            userDao.save(user);
            response.setViewName("redirect:/admin/userSearch");
        }
        return response;
    }
}