package com.chihuo.food.infrastructure.common.event;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);

	public void publish(DomainEvent event){
		LOGGER.info("=====Event begin published=====");
		LOGGER.info("Event publish param,id={},timestamp={},source={},data={}", event.getId(), event.getTimestamp(), event.getSource(), event.getData());
		Field[] declaredFields = event.getClass().getDeclaredFields();
		for(Field field : declaredFields) {
			field.setAccessible(true);
			// 获取属性
			String name = field.getName();
			// 获取属性值
			Object value = null;
			try {
				value = field.get(event);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			System.out.println("name=" + name + ",value=" + value);
		}
		//send to MQ
        //mq.send(event);
		LOGGER.info("=====Event end published=====");
    }
}