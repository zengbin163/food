package com.chihuo.food.domain.category.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Category {
    private Integer id;
    private Integer categoryTypeId;
    private Integer parentId;
    private String categoryName;
    private Date createTime;
    private Date updateTime;
}
