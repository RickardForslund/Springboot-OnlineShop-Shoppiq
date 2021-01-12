package com.shoppiq.repository;

import com.shoppiq.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {

    @Query("SELECT i FROM Item i WHERE i.sellerId = :id")
    Iterable<Item> findItemsBySellerId(Long id);

    Iterable<Item> findItemByName(String name);
}
