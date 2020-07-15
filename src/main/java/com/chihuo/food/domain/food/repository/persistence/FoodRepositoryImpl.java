package com.chihuo.food.domain.food.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.food.repository.facade.FoodRepository;
import com.chihuo.food.domain.food.repository.mapper.FoodDao;
import com.chihuo.food.domain.food.repository.po.FoodPO;

@Repository
public class FoodRepositoryImpl implements FoodRepository {
	
    @Autowired
    private FoodDao foodDao;

	@Override
	public Integer save(FoodPO foodPO) {
		this.foodDao.save(foodPO);
		return foodPO.getId();
	}

	@Override
	public void update(FoodPO foodPO) {
		this.foodDao.update(foodPO);
	}

	@Override
	public FoodPO findById(Integer id) {
		return this.foodDao.findById(id);
	}

	@Override
	public List<FoodPO> queryFoodList(Page<?> page, Integer firstCategoryId, Integer secondCategoryId, String foodName) {
		return this.foodDao.queryFoodList(page, firstCategoryId, secondCategoryId, foodName);
	}

}
