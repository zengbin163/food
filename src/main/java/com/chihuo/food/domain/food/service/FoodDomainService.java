package com.chihuo.food.domain.food.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.domain.food.event.FoodEvent;
import com.chihuo.food.domain.food.event.FoodEventType;
import com.chihuo.food.domain.food.repository.facade.FoodRepository;
import com.chihuo.food.domain.food.repository.po.FoodPO;
import com.chihuo.food.infrastructure.common.event.EventPublisher;

import cn.hutool.core.util.EscapeUtil;

@Service
public class FoodDomainService {

    @Autowired
    private EventPublisher eventPublisher;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodFactory foodFactory;


    public void create(Food food) {
    	if(StringUtils.isBlank(food.getFoodName())) {
    		throw new IllegalArgumentException("foodName is null");
    	}
    	
    	if(StringUtils.isBlank(food.getFoodPic())) {
    		throw new IllegalArgumentException("foodPic is null");
    	}
    	
    	if(StringUtils.isBlank(food.getFoodInfo())) {
    		throw new IllegalArgumentException("foodInfo is null");
    	} else {
    		String foodInfo = food.getFoodInfo();
    		food.setFoodInfo(EscapeUtil.unescape(foodInfo));
    	}
    	
    	if(null == food.getCategory().getId()) {
    		throw new IllegalArgumentException("category id is null");
    	}
    	
    	this.eventPublisher.publish(FoodEvent.create(FoodEventType.PUBLISH_EVENT, food));
    	
        this.foodRepository.save(foodFactory.createFoodPO(food));
    }

    public void update(Food food) {
    	if(StringUtils.isNotBlank(food.getFoodInfo())) {
    		String foodInfo = food.getFoodInfo();
    		food.setFoodInfo(EscapeUtil.unescape(foodInfo));
    	}
    	
    	this.foodRepository.update(foodFactory.createFoodPO(food));
    }

    public Food findById(Integer id) {
    	FoodPO po = this.foodRepository.findById(id);
        return this.foodFactory.createFood(po);
    }

	public IPage<Food> queryFoodList(Integer current, Integer size, Integer firstCategoryId, Integer secondCategoryId, String foodName) {
		Page<?> page = new Page<Food>(current, size);
		IPage<FoodPO> iPage = this.foodRepository.queryFoodList(page, firstCategoryId, secondCategoryId, foodName);
		return this.foodFactory.createFoodPage(iPage);
	}

}