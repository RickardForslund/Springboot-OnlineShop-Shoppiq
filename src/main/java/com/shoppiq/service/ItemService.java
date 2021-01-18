package com.shoppiq.service;

import com.shoppiq.entity.Item;
import com.shoppiq.enums.Category;
import com.shoppiq.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Iterable<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public Iterable<Item> findByName(String name) { return itemRepository.findItemByNameContainingIgnoreCase(name); }

    public Object findItemByNameContainingIgnoreCase(String name) {
        return itemRepository.findItemByNameContainingIgnoreCase(name);
    }

    public Object findItemByCategoryOrderByIdDesc(Category categoryEnum) {
        return itemRepository.findItemByCategoryOrderByIdDesc(categoryEnum);
    }

    public Object findItemByCategoryOrderByPriceAsc(Category categoryEnum) {
        return itemRepository.findItemByCategoryOrderByPriceAsc(categoryEnum);
    }

    public Object findItemByCategoryOrderByPriceDesc(Category categoryEnum) {
        return itemRepository.findItemByCategoryOrderByPriceDesc(categoryEnum);
    }
}
