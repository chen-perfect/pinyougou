package com.pinyougou.mapper;

import com.pinyougou.pojo.Seller;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * SellerMapper 数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:08
 * @version 1.0
 */
public interface SellerMapper extends Mapper<Seller>{

    /** 多条件查询商家 */
    List<Seller> findAll(@Param("seller") Seller seller);
}