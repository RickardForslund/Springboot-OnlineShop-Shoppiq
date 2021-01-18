package com.shoppiq.util;

import com.shoppiq.controller.UserController;
import com.shoppiq.entity.*;
import com.shoppiq.enums.Category;
import com.shoppiq.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class SampleDataGenerator {

    UserRepository userRepository;
    ItemRepository itemRepository;
    OrderRepository orderRepository;
    AuthGroupRepository authGroupRepository;
    UserController userController;

    @PostConstruct
    public void initData() {
        Address address1 = new Address("Country 1", "City 1", "Street 1", "1", "1", "none");
        Address address2 = new Address("Country 2", "City 2", "Street 2", "2", "2", "none");
        Address address3 = new Address("Country 3", "City 3", "Street 3", "3", "3", "none");
        Address address4 = new Address("Country 4", "City 4", "Street 4", "4", "4", "none");
        Address address5 = new Address("Country 5", "City 5", "Street 5", "5", "5", "none");
        Address address6 = new Address("Country 6", "City 6", "Street 6", "6", "6", "none");
        Address address7 = new Address("Country 7", "City 7", "Street 7", "7", "7", "none");

        Item item1 = new Item("TV", 1999.00, 5, Category.ELECTRONICS, "A widescreen TV");
        Item item2 = new Item("VHS Player", 99.00, 1, Category.ELECTRONICS, "An outdated but lovable piece of technology");
        Item item3 = new Item("Jacket", 499.00, 3, Category.CLOTHES, "A stylish blue jacket");
        Item item4 = new Item("Socks", 49.00, 20, Category.CLOTHES, "A pair of colourful socks");
        Item item5 = new Item("LEGO", 299.00, 25, Category.TOYS, "A classic childrens toy");
        Item item6 = new Item("Doll", 30.00, 10, Category.TOYS, "A handmade cloth doll");
        var itemList = Arrays.asList(item1, item2, item3, item4, item5, item6);
        itemRepository.saveAll(itemList);

        var items1 = Arrays.asList(item1, item2);
        var items2 = Arrays.asList(item6, item3, item5);
        var items3 = Arrays.asList(item4);

        User user1 = new User("user1", "pass1", "user1@email.com", "0701", address1, items1);
        User user2 = new User("user2", "pass2", "user2@email.com", "0702", address2);
        User user3 = new User("user3", "pass3", "user3@email.com", "0703", address3, items2);
        User user4 = new User("user4", "pass4", "user4@email.com", "0704", address4);
        User user5 = new User("user5", "pass5", "user5@email.com", "0705", address5);
        User admin = new User("admin", "adminpass", "admin@email.com", "0706", address6);
        User emily = new User("emily", "123", "emilysawesome@email.com", "0705",address7);

        authGroupRepository.save(new AuthGroup(admin.getUsername(), "ADMIN"));
        authGroupRepository.save(new AuthGroup(emily.getUsername(), "ADMIN"));
//        authGroupRepository.save(new AuthGroup(user1.getUsername(), "USER"));
//        authGroupRepository.save(new AuthGroup(user2.getUsername(), "USER"));
//        authGroupRepository.save(new AuthGroup(user3.getUsername(), "USER"));
//        authGroupRepository.save(new AuthGroup(user4.getUsername(), "USER"));
//        authGroupRepository.save(new AuthGroup(user5.getUsername(), "USER"));
        userController.saveUser(user1);
        userController.saveUser(user2);
        userController.saveUser(user3);
        userController.saveUser(user4);
        userController.saveUser(user5);
        userController.saveUser(admin);
        userController.saveUser(emily);
//        var userList = Arrays.asList(user1, user2, user3, user4, user5, admin, emily);

        OrderDetails orderDetails1 = new OrderDetails(item1.getId(), item1.getName(), item1.getPrice(), 1);
        OrderDetails orderDetails2 = new OrderDetails(item2.getId(), item2.getName(), item2.getPrice(), 1);
        OrderDetails orderDetails3 = new OrderDetails(item3.getId(), item3.getName(), item3.getPrice(), 1);
        OrderDetails orderDetails4 = new OrderDetails(item4.getId(), item3.getName(), item4.getPrice(), 5);
        OrderDetails orderDetails5 = new OrderDetails(item5.getId(), item3.getName(), item5.getPrice(), 2);


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
