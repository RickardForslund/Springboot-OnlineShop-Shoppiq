package com.shoppiq.controller;

import com.shoppiq.entity.Item;
import com.shoppiq.entity.User;
import com.shoppiq.enums.Category;
import com.shoppiq.service.ItemService;
import com.shoppiq.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller //Frontend
//@RestController //Insomnia
@RequestMapping("/api/v1/item") //TODO change urls. to make it better for frontend use
public class ItemController {

    //<editor-fold desc="Fields and constructor">
    private final ItemService itemService;
    private final UserService userService;

    public ItemController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }
    //</editor-fold>

    //<editor-fold desc="Create item">
    @GetMapping("/create")
    public String showCreateItemForm(Item item) {
        return "create-item";
    }

    @PostMapping("/add")
    public String saveItem(Item item, String username, BindingResult result, Model model) {
        if (result.hasErrors())
            return "error";
        User user = userService.findByUsername(username).get();
        user.addItem(item);
        itemService.saveItem(item);

         return "redirect:/api/v1/item/list";
    }
    //</editor-fold>

    //<editor-fold desc="Show items all / byId">
    @GetMapping("/list")
    public String showListItems(Model model) {
        model.addAttribute("items", itemService.findAllItems());
        return "list-items";
    }

    @GetMapping("/view/{id}")
    public String showItemByItemId(Model model, @PathVariable Long id) {
        Item item = null;
        try {
            item = itemService.findById(id).get();
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Item not found");
        }
        model.addAttribute("item",item);
        return "item";
    }
    //</editor-fold>

    //<editor-fold desc="Search">
    @GetMapping("/search")
    public String showSearchPage() {
        return "item-search";
    }

    @GetMapping("/search/name")
    public String searchItemByName(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("search", itemService.findItemByNameContainingIgnoreCase(name));
        return "item-search";
    }

    @GetMapping("/search/category")
    public String searchItemByCategory(Model model, @RequestParam(value = "category", required = false) String category) {
        try {
            var categoryEnum = Category.valueOf(category);
            model.addAttribute("search", itemService.findItemByCategoryOrderByIdDesc(categoryEnum));
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return "item-search";
    }

    @GetMapping("/search/category/asc")
    public String searchItemByCategoryPriceAsc(Model model, @RequestParam(value = "category", required = false) String category) {
        try {
            var categoryEnum = Category.valueOf(category);
            model.addAttribute("search", itemService.findItemByCategoryOrderByPriceAsc(categoryEnum));
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return "item-search";
    }

    @GetMapping("/search/category/desc")
    public String searchItemByCategoryPriceDesc(Model model, @RequestParam(value = "category", required = false) String category) {
        try {
            var categoryEnum = Category.valueOf(category);
            model.addAttribute("search", itemService.findItemByCategoryOrderByPriceDesc(categoryEnum));
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return "item-search";
    }
    //</editor-fold>

    //<editor-fold desc="setSellerId">
    @PostMapping("/{itemId}/set/seller/{sellerId}")
    public void setSellerId(@PathVariable Long itemId, @PathVariable Long sellerId) {
        Item item = itemService.findById(itemId).get();
        var user = userService.findById(sellerId);
        user.ifPresent(item::setSellerId);
        itemService.saveItem(item);
    }
    //</editor-fold>

    //<editor-fold desc="Old methods">
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
    //</editor-fold>

}
