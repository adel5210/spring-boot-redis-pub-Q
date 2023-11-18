package com.adel.mq.redisqueue.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adel.mq.redisqueue.model.Cevent;

@Repository
public interface CeventRepository extends CrudRepository<Cevent, String>{
    
}
