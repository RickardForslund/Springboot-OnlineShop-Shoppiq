package com.shoppiq.repository;

import com.shoppiq.entity.Item;
import com.shoppiq.entity.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails,Long> {
}
