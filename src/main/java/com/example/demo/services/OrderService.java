package com.example.demo.services;

import com.example.demo.DemoApplication;
import com.example.demo.entities.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    public List<Order> getOrdersByDateInterval(LocalDate beginDate, LocalDate finalDate){
        List<Order> orders = getOrders();
        List<Order> filteredOrders = new ArrayList<>();
        for(Order order : orders){
            if(order.getDate().isAfter(beginDate.atStartOfDay())
                    && order.getDate().isBefore(finalDate.atStartOfDay())){
                filteredOrders.add(order);
            }
        }

        return filteredOrders;
    }

    private List<Order> getOrders() {
        try {
            final String url = "http://www.mocky.io/v2/5817803a1000007d01cc7fc9";
            ObjectMapper jsonMapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule());
            return jsonMapper.readValue(new URL(url),
                    jsonMapper.getTypeFactory().constructCollectionType(List.class, Order.class));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON data: " + e.getMessage());
        }
    }

}
