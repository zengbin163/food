package com.chihuo.food.infrastructure.consumer;

import org.springframework.cloud.openfeign.FeignClient;

import com.chihuo.api.component.ProducerComponent;

@FeignClient(value = "kafka-notify-service", fallback = MessageProducerComponentFallback.class)
public interface MessageProducerComponent extends ProducerComponent {
}