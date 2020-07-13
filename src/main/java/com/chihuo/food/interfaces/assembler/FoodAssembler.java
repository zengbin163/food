package com.chihuo.food.interfaces.assembler;

import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.interfaces.dto.FoodDTO;

public class FoodAssembler {

	public static FoodDTO toDTO(Food food) {
		FoodDTO dto = new FoodDTO();
		dto.setId(food.getId());
		dto.setCategoryId(food.getCategory() != null ? food.getCategory().getId() : null);
		dto.setFoodName(food.getFoodName());
		dto.setFoodPic(food.getFoodPic());
		dto.setFoodInfo(food.getFoodInfo());
		dto.setCreateTime(food.getCreateTime());
		dto.setUpdateTime(food.getUpdateTime());
		return dto;
	}

	public static Food toDO(FoodDTO dto) {
		Food food = new Food();
		food.setId(dto.getId());
		if(null != dto.getCategoryId()) {
			Category category = new Category();
			category.setId(dto.getCategoryId());
			food.setCategory(category);
		}
		food.setFoodName(dto.getFoodName());
		food.setFoodPic(dto.getFoodPic());
		food.setFoodInfo(dto.getFoodInfo());
		food.setCreateTime(dto.getCreateTime());
		food.setUpdateTime(dto.getUpdateTime());
		return food;
	}
	
}
