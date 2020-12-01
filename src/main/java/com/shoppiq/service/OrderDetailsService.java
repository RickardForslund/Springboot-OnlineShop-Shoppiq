package com.shoppiq.service;

import com.shoppiq.entity.Item;
import com.shoppiq.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
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

}
