package com.chihuo.food.domain.food.repository.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.food.repository.po.FoodPO;

public interface FoodDao extends BaseMapper<FoodPO> {

	void save(@Param("foodPO") FoodPO foodPO);

	void update(@Param("foodPO") FoodPO foodPO);

	FoodPO findById(Integer id);

	IPage<FoodPO> queryFoodList(Page<?> page, @Param("firstCategoryId") Integer firstCategoryId, @Param("secondCategoryId") Integer secondCategoryId, @Param("foodName") String foodName);

}
