package com.chihuo.food.domain.category.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.domain.category.repository.facade.CategoryRepository;
import com.chihuo.food.domain.category.repository.po.CategoryPO;

@Service
public class CategoryFactory {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryPO createCategoryPO(Category category){
    	CategoryPO categoryPO = new CategoryPO();
    	categoryPO.setId(category.getId());
    	categoryPO.setCategoryTypeId(category.getCategoryTypeId());
    	categoryPO.setParentId(category.getParentId());
    	categoryPO.setCategoryName(category.getCategoryName());
    	categoryPO.setCreateTime(category.getCreateTime());
    	categoryPO.setUpdateTime(category.getUpdateTime());
        return categoryPO;
    }

	public Category createCategory(CategoryPO po) {
		Category category = new Category();
		category.setId(po.getId());
		category.setCategoryTypeId(po.getCategoryTypeId());
		category.setParentId(po.getParentId());
		category.setCategoryName(po.getCategoryName());
		category.setCreateTime(po.getCreateTime());
		category.setUpdateTime(po.getUpdateTime());
		return category;
	}
	
	public List<Category> createCategoryList(List<CategoryPO> poList) {
		List<Category> list = new ArrayList<Category>();
		if(CollectionUtils.isEmpty(poList)) {
			return list;
		}
		for(CategoryPO po : poList) {
			Category category = new Category();
			category.setId(po.getId());
			category.setCategoryTypeId(po.getCategoryTypeId());
			category.setParentId(po.getParentId());
			category.setCategoryName(po.getCategoryName());
			category.setCreateTime(po.getCreateTime());
			category.setUpdateTime(po.getUpdateTime());
			list.add(category);
		}
		return list;
	}

    public Category getCategory(CategoryPO categoryPO){
    	categoryPO = categoryRepository.findById(categoryPO.getId());
        return createCategory(categoryPO);
    }

}
