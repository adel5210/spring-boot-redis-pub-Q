package com.adel.mq.redisqueue.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.adel.mq.redisqueue.model.Cevent;
import com.adel.mq.redisqueue.repository.CeventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherService implements CommandLineRunner {

    private final RedisTemplate<String, String> credisTemplate;
    private final CeventRepository ceventRepository;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initiate publisher ...");
        final List<CompletableFuture<Cevent>> processCevents = new ArrayList<>();
        for (long i = 1; i < 1000; i++) {
            final Cevent cevent = new Cevent(String.valueOf(i), UUID.randomUUID().toString());

            processCevents.add(CompletableFuture.supplyAsync(() -> ceventRepository.save(cevent), executorService));

            final ObjectMapper mapper = new ObjectMapper();
            final String jsonString = mapper.writeValueAsString(cevent);
            this.credisTemplate.convertAndSend("topic0", jsonString);
            System.out.println(jsonString);
        }

        Executors.newCachedThreadPool().execute(() -> {
            processCevents.forEach(e -> 
                e.thenAccept(e2 -> ceventRepository.delete(e2))
            );
        });

    }

}
