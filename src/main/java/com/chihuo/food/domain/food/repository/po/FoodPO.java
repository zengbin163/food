package com.chihuo.food.domain.food.repository.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.chihuo.food.domain.category.repository.po.CategoryPO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FoodPO {
	@TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private CategoryPO parentCategoryPO;
    private CategoryPO categoryPO;
    private String foodName;
    private String foodPic;
    private String foodInfo;
    private Date createTime;
    private Date updateTime;
}
