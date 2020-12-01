package com.shoppiq.controller;

import com.shoppiq.entity.Item;
import com.shoppiq.service.ItemService;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item saveItem(@RequestBody Item item) {
        return itemService.saveItem(item);
       // var savedItem = itemService.saveItem(item);
       // return Response.ok(savedItem).build();
    }

    @GetMapping
    public Iterable<Item> findAllItems() {
        return itemService.findAllItems();
    }

    @GetMapping("/{id}")
    public Optional<Item> findItemById(@PathVariable Long id) {
        return itemService.findById(id);
    }

}
