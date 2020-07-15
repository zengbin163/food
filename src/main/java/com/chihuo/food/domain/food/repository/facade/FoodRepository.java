package com.chihuo.food.domain.food.repository.facade;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.food.repository.po.FoodPO;

public interface FoodRepository {

	Integer save(FoodPO foodPO);

	void update(FoodPO foodPO);

	FoodPO findById(Integer id);

	List<FoodPO> queryFoodList(Page<?> page, Integer firstCategoryId, Integer secondCategoryId, String foodName);

}
