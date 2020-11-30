package com.shoppiq.service;

import com.shoppiq.entity.Item;
import com.shoppiq.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {


    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

}
