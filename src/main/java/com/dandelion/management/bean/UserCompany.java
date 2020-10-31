package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户公司视图表实体类
 *
 * @author hongjian Yang
 * @date 2020/8/16
 */
@Data
@TableName("v_user_company")
public class UserCompany {
    /**
     *  primary key
     */
    @TableId(value="user_id",type= IdType.AUTO)
    private Integer userId;
    /**
     * username
     */
    private String name;
    /**
     * user phone number
     */
    private String phone;
    /**
     * user company name
     */
    private String companyName;
}
