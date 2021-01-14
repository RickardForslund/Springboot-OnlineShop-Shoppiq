package com.shoppiq.repository;

import com.shoppiq.entity.AuthGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AuthGroupRepository extends CrudRepository<AuthGroup, Long> {
    List<AuthGroup> findByUsername(String username);
}
