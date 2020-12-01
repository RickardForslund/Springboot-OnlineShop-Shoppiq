package com.shoppiq.repository;

import com.shoppiq.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Item,Long> {
}
