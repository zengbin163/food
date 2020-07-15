package com.chihuo.food.domain.food.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.food.domain.food.repository.facade.FoodItemRepository;
import com.chihuo.food.domain.food.repository.mapper.FoodItemDao;
import com.chihuo.food.domain.food.repository.po.FoodItemPO;

@Repository
public class FoodItemRepositoryImpl implements FoodItemRepository {
	
    @Autowired
    private FoodItemDao foodItemDao;

	@Override
	public Integer save(FoodItemPO foodItemPO) {
		this.foodItemDao.save(foodItemPO);
		return foodItemPO.getId();
	}

	@Override
	public void delete(Integer id) {
		this.foodItemDao.delete(id);
	}

	@Override
	public FoodItemPO findById(Integer id) {
		return this.foodItemDao.findById(id);
	}

	@Override
	public List<FoodItemPO> queryFoodItemListByFoodId(Integer foodId) {
		return this.foodItemDao.queryFoodItemListByFoodId(foodId);
	}

}
