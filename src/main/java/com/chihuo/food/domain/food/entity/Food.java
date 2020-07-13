package com.chihuo.food.domain.food.entity;

import java.util.Date;

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
    private String foodName;
    private String foodPic;
    private String foodInfo;
    private Date createTime;
    private Date updateTime;
}
