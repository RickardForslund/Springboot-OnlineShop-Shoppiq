//package com.shoppiq.service;
//
//import com.shoppiq.entity.Item;
//import com.shoppiq.enums.Category;
//import com.shoppiq.repository.ItemRepository;
//
//import org.junit.jupiter.api.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import javax.persistence.EntityManager;
//import static org.junit.jupiter.api.Assertions.*;
//
////This test class has been deprecated in favor of ItemControllerTest
//
//@Deprecated
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class ItemServiceTest {
//
//    Item item;
//    @Autowired
//    EntityManager entityManager;
//    @Autowired
//    ItemRepository itemRepository;
//
//    @Disabled
//    @BeforeEach
//    void setUp() {
//        item = new Item("Stolströja", 99, 1, Category.CLOTHES, "En tröja med ett stols märke.");
//        itemRepository.save(item);
//    }
//
//    @Disabled
//    @Test
//    void saveItem() {
//        var newItem = itemRepository.save(item);
//        assertEquals(item, newItem);
//    }
//
//    @Disabled
//    @Test
//    void findAllItems() {
//        assertEquals(itemRepository.findById((long) 1).get(), itemRepository.findAll().iterator().next());
//    }
//
//    @Disabled
//    @Test
//    @Order(1)
//    void findById() {
//        assertEquals(item, itemRepository.findById((long) 1).get());
//    }
//
//}