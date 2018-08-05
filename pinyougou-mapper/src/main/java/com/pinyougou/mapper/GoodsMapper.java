package com.pinyougou.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import com.pinyougou.pojo.Goods;

import java.util.List;
import java.util.Map;

/**
 * GoodsMapper 数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:08
 * @version 1.0
 */
public interface GoodsMapper extends Mapper<Goods>{

    /**
     * 多条件查询商品
     */
    List<Map<String,Object>> findAll(@Param("goods")Goods goods);
}