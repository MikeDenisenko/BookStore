package com.mike.controller;

import com.mike.model.Order;
import com.mike.model.OrderDetail;
import com.mike.service.BookService;
import com.mike.service.OrderDetailService;
import com.mike.service.OrderService;
import com.mike.service.SecurityServiceImpl;
import com.mike.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    private OrderDetailService orderDetailService;
    private UserServiceImpl userService;
    private SecurityServiceImpl securityService;
    private OrderService orderService;
    private BookService bookService;

    @Autowired
    public OrderController (OrderDetailService orderDetailService, UserServiceImpl userService,
                            SecurityServiceImpl securityService, OrderService orderService,
                            BookService bookService) {
        this.orderDetailService = orderDetailService;
        this.userService = userService;
        this.securityService = securityService;
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping(value = "/order")
    public ModelAndView getOrderList() {
        List<Order> orders = orderService.findByUserName(securityService.findLoggedInUsername());
        ModelAndView modelAndView = new ModelAndView("OrdersListPage");
        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping(value = "/order/{id}")
    public ModelAndView getOrderDetails(@PathVariable("id") int id) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(id);
        ModelAndView modelAndView = new ModelAndView("OrderPage");
        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
        modelAndView.addObject("orderDetails", orderDetails);
        return modelAndView;
    }

//    @PostMapping(value = "/order/add")
//    public ModelAndView createOrder(@PathVariable("id") int bookId) {
//        Order order = new Order();
//        if (orderService.getOpenOrder(securityService.findLoggedInUsername()) == null) {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setBook(bookService.find(bookId));
//            orderDetail.setQuantityInOrder(1);
//            orderDetail.setPrice(orderDetail.getBook().getActualPrice());
//            List<OrderDetail> orderDetails = new ArrayList<>(List.of(orderDetail));
//            order.setUser(userService.findUserByName(securityService.findLoggedInUsername()));
//            order.setOrderStat("open");
//            order.setTotalPrice(orderDetail.getPrice());
//            order.setOrderDetails(orderDetails);
//            orderService.save(order);
//        } else {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setBook(bookService.find(bookId));
//            orderDetail.setQuantityInOrder(1);
//            orderDetail.setPrice(orderDetail.getBook().getActualPrice());
//            order = orderService.getOpenOrder(securityService.findLoggedInUsername());
//
//        }
//
//    }
//
//    public List<OrderDetail> addBook


}
