package com.adel.mq.redisqueue.publisher.service;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.adel.mq.redisqueue.publisher.model.Cevent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherService implements CommandLineRunner {

    private final RedisTemplate<String, String> credisTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("initiate publisher ...");
        for (long i = 1; i < 1000; i++) {
            this.credisTemplate.convertAndSend("topic0", new Cevent(i, UUID.randomUUID().toString()).toString());
        }
    }

}
