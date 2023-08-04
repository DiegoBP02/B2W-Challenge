package com.example.demo.controllers;

import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/challenge-backend")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/item")
    public ResponseEntity<List<Order>> findAll(
            @RequestParam("begindate") @DateTimeFormat(pattern = "dd-MM-yyyy")LocalDate beginDate,
            @RequestParam("finaldate") @DateTimeFormat(pattern = "dd-MM-yyyy")LocalDate finalDate
            ){
        List<Order> orders = orderService.getOrdersByDateInterval(beginDate,finalDate);
        return ResponseEntity.ok(orders);
    }

}
