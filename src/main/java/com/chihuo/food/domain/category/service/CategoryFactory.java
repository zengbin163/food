package com.chihuo.food.domain.category.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.domain.category.entity.CategoryItem;
import com.chihuo.food.domain.category.entity.CategoryType;
import com.chihuo.food.domain.category.repository.facade.CategoryRepository;
import com.chihuo.food.domain.category.repository.po.CategoryItemPO;
import com.chihuo.food.domain.category.repository.po.CategoryPO;
import com.chihuo.food.domain.category.repository.po.CategoryTypePO;

@Service
public class CategoryFactory {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryPO createCategoryPO(Category category){
    	return CategoryPO.builder().id(category.getId()).categoryTypeId(category.getCategoryTypeId()).parentId(category.getParentId()).categoryName(category.getCategoryName()).createTime(category.getCreateTime()).updateTime(category.getUpdateTime()).build();
    }

	public Category createCategory(CategoryPO po) {
    	return Category.builder().id(po.getId()).categoryTypeId(po.getCategoryTypeId()).parentId(po.getParentId()).categoryName(po.getCategoryName()).createTime(po.getCreateTime()).updateTime(po.getUpdateTime()).build();
	}
	
	public CategoryItemPO createCategoryItemPO(CategoryItem categoryItem){
		return CategoryItemPO.builder().id(categoryItem.getId()).categoryId(categoryItem.getCategoryId()).itemName(categoryItem.getItemName()).createTime(categoryItem.getCreateTime()).updateTime(categoryItem.getUpdateTime()).build();
	}
	
	public CategoryItem createCategoryItem(CategoryItemPO po) {
		CategoryItem item = CategoryItem.builder().id(po.getId()).categoryId(po.getCategoryId()).itemName(po.getItemName()).createTime(po.getCreateTime()).updateTime(po.getUpdateTime()).build();
		if (null != po.getCategoryPO()) {
			item.setCategory(createCategory(po.getCategoryPO()));
		}
		if (null != po.getParentCategoryPO()) {
			item.setParentCategory(createCategory(po.getParentCategoryPO()));
		}
		if (null != po.getCategoryTypePO()) {
			item.setCategoryType(createCategoryType(po.getCategoryTypePO()));
		}
		return item;
	}
	
	public CategoryType createCategoryType(CategoryTypePO po) {
    	return CategoryType.builder().id(po.getId()).typeName(po.getTypeName()).createTime(po.getCreateTime()).updateTime(po.getUpdateTime()).build();
	}
	
	public List<Category> createCategoryList(List<CategoryPO> poList) {
		List<Category> list = new ArrayList<Category>();
		if(CollectionUtils.isEmpty(poList)) {
			return list;
		}
		for(CategoryPO po : poList) {
			list.add(createCategory(po));
		}
		return list;
	}
	
	public List<CategoryType> createCategoryTypeList(List<CategoryTypePO> poList) {
		List<CategoryType> list = new ArrayList<CategoryType>();
		if(CollectionUtils.isEmpty(poList)) {
			return list;
		}
		for(CategoryTypePO po : poList) {
			list.add(this.createCategoryType(po));
		}
		return list;
	}
	
	public List<CategoryItem> createCategoryItemList(List<CategoryItemPO> poList) {
		List<CategoryItem> list = new ArrayList<CategoryItem>();
		if(CollectionUtils.isEmpty(poList)) {
			return list;
		}
		for(CategoryItemPO po : poList) {
			CategoryItem categoryItem = this.createCategoryItem(po);
			CategoryTypePO typePO = po.getCategoryTypePO();
			if(typePO != null) {
				categoryItem.setCategoryType(createCategoryType(typePO));
			}
			CategoryPO parentCategoryPO = po.getParentCategoryPO();
			if(parentCategoryPO != null) {
				categoryItem.setParentCategory(createCategory(parentCategoryPO));
			}
			CategoryPO categoryPO = po.getCategoryPO();
			if(categoryPO != null) {
				categoryItem.setCategory(createCategory(categoryPO));
			}
			list.add(categoryItem);
		}
		return list;
	}
	
	public IPage<CategoryItem> createCateItemPage(IPage<CategoryItemPO> pagePO) {
		IPage<CategoryItem> page = new Page<CategoryItem>();
		page.setCurrent(pagePO.getCurrent());
		page.setPages(pagePO.getPages());
		page.setRecords(this.createCategoryItemList(pagePO.getRecords()));
		page.setSize(pagePO.getSize());
		page.setTotal(pagePO.getTotal());
		return page;
	}

    public Category getCategory(CategoryPO categoryPO){
    	categoryPO = categoryRepository.findById(categoryPO.getId());
        return createCategory(categoryPO);
    }

}
