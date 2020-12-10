package com.shoppiq.controller;

import com.shoppiq.entity.Item;
import com.shoppiq.repository.ItemRepository;
import com.shoppiq.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller //Frontend
//@RestController //Insomnia
@RequestMapping("/api/v1/item") //TODO change urls. to make it better for frontend use
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    public ItemController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    //<editor-fold desc="Create item">
    @GetMapping("/create")
    public String showCreateItemForm(Item item) {
        return "create-item";
    }

    @PostMapping("/add")
    public String saveItem(Item item, BindingResult result, Model model) {
        if (result.hasErrors())
            return "error";
        itemRepository.save(item);
        return "home";
    }
    //</editor-fold>

    //TODO add frontend
    @GetMapping
    public Iterable<Item> findAllItems() {
        return itemService.findAllItems();
    }

    //TODO add frontend
    @GetMapping("/{id}")
    public Optional<Item> findItemById(@PathVariable Long id) {
        return itemService.findById(id);
    }

}
