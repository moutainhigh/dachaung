package com.dandelion.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dandelion.management.bean.Asset;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * AssetMapper interface
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
public interface AssetMapper extends BaseMapper<Asset> {

    /**
     *  @author: Sun Qingxin
     *  @Date: 2020/8/1 16:11
     *  @Description: 得到所有的查询结果
     */
    @Select("select * from t_asset")
    List<Asset> getAll();
}
