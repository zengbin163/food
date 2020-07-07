package com.chihuo.food.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.domain.category.service.CategoryDomainService;

@Service
public class CategoryApplicationService {
	
    @Autowired
    private CategoryDomainService categoryDomainService;

    public void createCategory(Category category){
    	this.categoryDomainService.create(category);
    }
    
    public void updateCategory(Category category){
    	this.categoryDomainService.update(category);
    }
    
    public Category findById(Integer id) {
        return this.categoryDomainService.findById(id);
    }

    public List<Category> queryCategoryListByParentId(Integer parentId) {
    	return this.categoryDomainService.queryCategoryListByParentId(parentId);
    }
    
}
