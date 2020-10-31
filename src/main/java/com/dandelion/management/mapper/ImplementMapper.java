package com.dandelion.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dandelion.management.bean.Implement;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/9 15:33
 *  @Description:
 */
public interface ImplementMapper extends BaseMapper<Implement> {
    /**
     * 得到所有信息
     * @return
     */
    @Select("select * from t_implement")
    List<Implement> getAll();
}
