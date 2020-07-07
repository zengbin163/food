package com.chihuo.food.domain.category.repository.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.chihuo.food.domain.category.entity.Category;

import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class CategoryPO {
	@Id
	@Column(name = "id")
	@GenericGenerator(name = "idGenerator", strategy = "increment")
	@GeneratedValue(generator = "idGenerator", strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer categoryTypeId;
	private Integer parentId;
	private String categoryName;
	private Date createTime;
	private Date updateTime;

	public Category toCategory() {
		return new Category();
	}
}
