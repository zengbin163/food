package com.chihuo.food.domain.food.repository.facade;

import java.util.List;

import com.chihuo.food.domain.food.repository.po.FoodItemPO;

public interface FoodItemRepository {

	Integer save(FoodItemPO foodItemPO);

	void delete(Integer id);

	FoodItemPO findById(Integer id);

	List<FoodItemPO> queryFoodItemListByFoodId(Integer foodId);

}
