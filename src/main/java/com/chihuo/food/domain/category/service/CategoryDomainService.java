package com.chihuo.food.domain.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.domain.category.repository.facade.CategoryRepository;
import com.chihuo.food.domain.category.repository.po.CategoryPO;

@Service
public class CategoryDomainService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryFactory categoryFactory;


    public void create(Category category) {
    	CategoryPO categoryPO = categoryRepository.findById(category.getId());
        if (null != categoryPO) {
            throw new RuntimeException("Category already exists");
        }
        this.categoryRepository.save(categoryFactory.createCategoryPO(category));
    }

    public void update(Category category) {
    	this.categoryRepository.update(categoryFactory.createCategoryPO(category));
    }

    public Category findById(Integer id) {
    	CategoryPO categoryPO = categoryRepository.findById(id);
        return this.categoryFactory.getCategory(categoryPO);
    }

    public List<Category> queryCategoryListByParentId(Integer parentId) {
    	if(null == parentId) {
    		throw new IllegalArgumentException("parentId is null");
    	}
    	List<CategoryPO> poList = this.categoryRepository.queryCategoryListByParentId(parentId);
    	return this.categoryFactory.createCategoryList(poList);
    }
}