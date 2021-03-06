package com.chihuo.food.domain.user.repository.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPO {
	
    private Long uid;
    private String userName;
    private String mobile;
    private String password;
    private Date createTime;
    
}
