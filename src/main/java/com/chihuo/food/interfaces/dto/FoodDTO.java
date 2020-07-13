package com.chihuo.food.interfaces.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FoodDTO {
	private Integer id;
	private Integer categoryId;
	private String foodName;
	private String foodPic;
	private String foodInfo;
	private Date createTime;
	private Date updateTime;

	private Integer current;
	private Integer size;
	private Integer firstCategoryId;
	private Integer secondCategoryId;
}
