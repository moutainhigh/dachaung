package com.dandelion.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dandelion.management.bean.EventInfo;
/**
 * 基于事件的数据视图 目前仅作为查询依据(需在非空数据全部填写完整后 才可通过视图进行查询)
 *
 * @author hongjianYang
 * @date 2020/7/27
 */
public interface EventInfoMapper extends BaseMapper<EventInfo> {
}
