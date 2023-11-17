package com.adel.mq.redisqueue.publisher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cevent {
    private Long id;
    private String token;
    
    @Override
    public String toString() {
        return "Event [id=" + id + ", token=" + token + "]";
    }
    
}
