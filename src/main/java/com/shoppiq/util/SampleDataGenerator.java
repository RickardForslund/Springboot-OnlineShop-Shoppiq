package com.shoppiq.util;

import com.shoppiq.entity.*;
import com.shoppiq.enums.Category;
import com.shoppiq.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class SampleDataGenerator {

    UserRepository userRepository;
    ItemRepository itemRepository;
    OrderRepository orderRepository;

    @PostConstruct
    public void initData() {
        Address address1 = new Address("Country 1", "City 1", "Street 1", "1", "1", "none");
        Address address2 = new Address("Country 2", "City 2", "Street 2", "2", "2", "none");
        Address address3 = new Address("Country 3", "City 3", "Street 3", "3", "3", "none");
        Address address4 = new Address("Country 4", "City 4", "Street 4", "4", "4", "none");
        Address address5 = new Address("Country 5", "City 5", "Street 5", "5", "5", "none");

        User user1 = new User("user1", "pass1", "user1@email.com", "0701", address1);
        User user2 = new User("user2", "pass2", "user2@email.com", "0702", address2);
        User user3 = new User("user3", "pass3", "user3@email.com", "0703", address3);
        User user4 = new User("user4", "pass4", "user4@email.com", "0704", address4);
        User user5 = new User("user5", "pass5", "user5@email.com", "0705", address5);
        User admin1 = new User("admin1", "pass1", "admin1@email.com", "0705", address1);
        User admin2 = new User("admin2", "pass2", "admin2@email.com", "0705", address2);
        User emily = new User("emily", "123", "emilysawesome@email.com", "0705", address3);
        var userList = Arrays.asList(user1, user2, user3, user4, user5);
        userRepository.saveAll(userList);

        Item item1 = new Item("TV", 1999.00, 5, Category.ELECTRONICS, "Very descriptive text");
        Item item2 = new Item("VHS Player", 99.00, 1, Category.ELECTRONICS, "Very descriptive text");
        Item item3 = new Item("Jacket", 499.00, 3, Category.CLOTHES, "Very descriptive text");
        Item item4 = new Item("Socks", 49.00, 20, Category.CLOTHES, "Very descriptive text");
        Item item5 = new Item("LEGO", 299.00, 25, Category.TOYS, "Very descriptive text");
        var itemList = Arrays.asList(item1, item2, item3, item4, item5);
        itemRepository.saveAll(itemList);

        OrderDetails orderDetails1 = new OrderDetails(item1.getId(), item1.getName(),item1.getPrice(), 1);
        OrderDetails orderDetails2 = new OrderDetails(item2.getId(), item2.getName(),item2.getPrice(), 1);
        OrderDetails orderDetails3 = new OrderDetails(item3.getId(), item3.getName(),item3.getPrice(), 1);
        OrderDetails orderDetails4 = new OrderDetails(item4.getId(), item3.getName(),item4.getPrice(), 5);
        OrderDetails orderDetails5 = new OrderDetails(item5.getId(), item3.getName(),item5.getPrice(), 2);
        var orderDetailsList = Arrays.asList(orderDetails1, orderDetails2, orderDetails3, orderDetails4, orderDetails5);
        var electronicsOrderList = Arrays.asList(orderDetails1, orderDetails2);
        var clothesOrderList = Arrays.asList(orderDetails3, orderDetails4);
        var toysOrderList = Arrays.asList(orderDetails5);

        Orders order1 = new Orders(user1);
        Orders order2 = new Orders(user2);
        Orders order3 = new Orders(user3);
        Orders order4 = new Orders(user4);
        order1.addOrderDetails(orderDetailsList);
        order2.addOrderDetails(electronicsOrderList);
        order3.addOrderDetails(clothesOrderList);
        order4.addOrderDetails(toysOrderList);
        var ordersList = Arrays.asList(order1, order2, order3, order4);
        orderRepository.saveAll(ordersList);
    }

}
