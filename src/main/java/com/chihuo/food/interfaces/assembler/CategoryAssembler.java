package com.chihuo.food.interfaces.assembler;

import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.interfaces.dto.CategoryDTO;

public class CategoryAssembler {

	public static CategoryDTO toDTO(Category category) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(category.getId());
		dto.setParentId(category.getParentId());
		dto.setCategoryTypeId(category.getCategoryTypeId());
		dto.setCategoryName(category.getCategoryName());
		return dto;
	}

	public static Category toDO(CategoryDTO dto) {
		Category category = new Category();
		category.setId(dto.getId());
		category.setParentId(dto.getParentId());
		category.setCategoryTypeId(dto.getCategoryTypeId());
		category.setCategoryName(dto.getCategoryName());
		return category;
	}
}
