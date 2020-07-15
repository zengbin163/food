package com.chihuo.food.domain.food.repository.facade;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.food.repository.po.FoodPO;

public interface FoodRepository {

	Integer save(FoodPO foodPO);

	void update(FoodPO foodPO);

	FoodPO findById(Integer id);

	IPage<FoodPO> queryFoodList(Page<?> page, Integer firstCategoryId, Integer secondCategoryId, String foodName);

}
