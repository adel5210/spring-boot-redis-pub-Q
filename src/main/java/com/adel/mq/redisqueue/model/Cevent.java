package com.adel.mq.redisqueue.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Cevent")
public class Cevent {
    
    @Id
    private String id;

    private String token;
    
    @Override
    public String toString() {
        return "Event [id=" + id + ", token=" + token + "]";
    }
    
}
