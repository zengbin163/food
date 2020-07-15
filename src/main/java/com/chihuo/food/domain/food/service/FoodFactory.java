package com.chihuo.food.domain.food.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.domain.category.repository.po.CategoryPO;
import com.chihuo.food.domain.category.service.CategoryFactory;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.domain.food.entity.FoodItem;
import com.chihuo.food.domain.food.repository.po.FoodItemPO;
import com.chihuo.food.domain.food.repository.po.FoodPO;

@Service
public class FoodFactory {
	
	@Autowired
	private CategoryFactory categoryFactory;
	
    public FoodPO createFoodPO(Food food) {
    	return FoodPO.builder().id(food.getId()).categoryPO(this.categoryFactory.createCategoryPO(food.getCategory())).foodName(food.getFoodName()).foodPic(food.getFoodPic()).foodInfo(food.getFoodInfo()).createTime(food.getCreateTime()).updateTime(food.getUpdateTime()).build();
    }

	public Food createFood(FoodPO po) {
		Category category = (null == po.getCategoryPO() ? null : this.categoryFactory.createCategory(po.getCategoryPO()));
    	return Food.builder().id(po.getId()).category(category).foodName(po.getFoodName()).foodPic(po.getFoodPic()).foodInfo(po.getFoodInfo()).createTime(po.getCreateTime()).updateTime(po.getUpdateTime()).build();
	}
	
	public FoodItemPO createFoodItemPO(FoodItem foodItem) {
		return FoodItemPO.builder().id(foodItem.getId()).categoryItemId(foodItem.getCategoryItemId()).foodId(foodItem.getFoodId()).createTime(foodItem.getCreateTime()).updateTime(foodItem.getUpdateTime()).build();
	}
	
	public FoodItem createFoodItem(FoodItemPO foodItemPO) {
		return FoodItem.builder().id(foodItemPO.getId()).categoryItemId(foodItemPO.getCategoryItemId()).foodId(foodItemPO.getFoodId()).createTime(foodItemPO.getCreateTime()).updateTime(foodItemPO.getUpdateTime()).build();
	}
	
	public List<Food> createFoodList(List<FoodPO> poList) {
		List<Food> list = new ArrayList<Food>();
		if(CollectionUtils.isEmpty(poList)) {
			return list;
		}
		for(FoodPO po : poList) {
			Food food = this.createFood(po);
			CategoryPO categoryPO = po.getCategoryPO();
			if(categoryPO != null) {
				food.setCategory(this.categoryFactory.createCategory(categoryPO));
			}
			CategoryPO parentCategoryPO = po.getParentCategoryPO();
			if(parentCategoryPO != null) {
				food.setParentCategory(this.categoryFactory.createCategory(parentCategoryPO));
			}
			list.add(food);
		}
		return list;
	}
	
	public IPage<Food> createFoodPage(IPage<FoodPO> pagePO) {
		IPage<Food> page = new Page<Food>();
		page.setCurrent(pagePO.getCurrent());
		page.setPages(pagePO.getPages());
		page.setRecords(this.createFoodList(pagePO.getRecords()));
		page.setSize(pagePO.getSize());
		page.setTotal(pagePO.getTotal());
		return page;
	}
	
}
