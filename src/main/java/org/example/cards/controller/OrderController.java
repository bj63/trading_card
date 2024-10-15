package org.example.cards.controller;
import lombok.extern.slf4j.Slf4j;
import org.example.cards.database.entity.Order;
import org.example.cards.database.entity.OrderDetail;
import org.example.cards.database.dao.CardDAO;
import org.example.cards.database.dao.OrderDAO;
import org.example.cards.database.dao.OrderDetailsDAO;
import org.example.cards.database.entity.Card;
import org.example.cards.database.entity.User;
import org.example.cards.security.AuthenticatedUserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class OrderController {

    @Autowired
    private OrderDAO orderDao;

    @Autowired
    private OrderDetailsDAO orderDetailsDao;

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    @GetMapping("/order/orderdetail")
    public ModelAndView orderDetail() {
        ModelAndView response = new ModelAndView("order/orderdetail");

        // get the logged in user
        User user = authenticatedUserUtilities.getCurrentUser();

        // now we need to get the order from the database where the status is 'CART'
        Order order = orderDao.findOrderInCartStatus(user.getId());

        if (order != null) {
            // get the order details for the order
            List<Map<String, Object>> orderDetails = orderDao.getOrderDetails(order.getId());
            response.addObject("orderDetails", orderDetails);

            // lets get the total order amount
            Double orderTotal = orderDao.getOrderTotal(order.getId());
            response.addObject("orderTotal", orderTotal);
        }

        return response;
    }

    @GetMapping("/order/addToCart")
    public ModelAndView addToCart(@RequestParam Integer cardId) {
        ModelAndView response = new ModelAndView();

        // first we can look up the card in the database given the incoming cardId
        Card card = cardDAO.findByCardId(cardId);

        // get the logged in user
        User user = authenticatedUserUtilities.getCurrentUser();

        // now we need to get the order from the database where the status is 'CART' and the user is the logged in user
        Order order = orderDao.findOrderInCartStatus(user.getId());
        if (order == null) {
            // the user does not have an order in cart status so we need to create one
            order = new Order();
            order.setUser(user);
            order.setOrderDate(new Date());
            order.setStatus("CART");

            orderDao.save(order);
        }

        // now we have to look to see if the card is already in the order details table
        OrderDetail orderDetail = orderDetailsDao.isCardInCart(order.getId(), cardId);
        if (orderDetail == null) {
            // this card is not part of this order so we can create a new orderdetails
            orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setCard(card);
            orderDetail.setQuantityOrdered(1);

            orderDetailsDao.save(orderDetail);
        } else {
            // the card is already in the cart so we need to increment the quantity
            orderDetail.setQuantityOrdered(orderDetail.getQuantityOrdered() + 1);
            orderDetailsDao.save(orderDetail);
        }

        response.setViewName("redirect:/order/orderdetail");
        return response;
    }

    @GetMapping("/order/checkout")
    public ModelAndView checkout() {
        ModelAndView response = new ModelAndView();

        // get the logged in user
        User user = authenticatedUserUtilities.getCurrentUser();

        // now we need to get the order from the database where the status is 'CART'
        Order order = orderDao.findOrderInCartStatus(user.getId());
        if (order == null) {
            log.error("There is no order with items in the cart to checkout");
        } else {
            // there was an order with items in the cart so we change the status to COMPLETE
            order.setStatus("COMPLETE");
            orderDao.save(order);
        }

        response.setViewName("redirect:/order/orderdetail");
        return response;
    }
}