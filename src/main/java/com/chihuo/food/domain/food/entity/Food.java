package com.chihuo.food.domain.food.entity;

import java.util.Date;
import java.util.List;

import com.chihuo.food.domain.category.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private Integer id;
    private Category category;
    private Category parentCategory;
    private String foodName;
    private String foodPic;
    private String foodInfo;
    private Date createTime;
    private Date updateTime;
    
    private List<FoodItem> foodItemList;
}