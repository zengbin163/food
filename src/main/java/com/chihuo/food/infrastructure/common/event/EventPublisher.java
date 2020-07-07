package com.chihuo.food.infrastructure.common.event;

import org.springframework.stereotype.Service;

import com.chihuo.food.domain.category.event.CategoryEvent;

@Service
public class EventPublisher {

    public void publish(CategoryEvent event){
        //send to MQ
        //mq.send(event);
    }
}