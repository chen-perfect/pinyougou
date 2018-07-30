package com.pinyougou.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import com.pinyougou.pojo.Seller;

import java.util.List;

/**
 * SellerMapper 数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:41
 * @version 1.0
 */
public interface SellerMapper extends Mapper<Seller> {


    List<Seller> findAll(@Param("seller") Seller seller);
}