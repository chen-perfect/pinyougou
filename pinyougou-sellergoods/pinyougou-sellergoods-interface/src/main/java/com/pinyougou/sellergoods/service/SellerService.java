package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.Seller;
import pojo.PageResult;

import java.util.List;
import java.io.Serializable;
/**
 * SellerService 服务接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:52
 * @version 1.0
 */
public interface SellerService {

	/** 添加方法 */
	void save(Seller seller);

	/** 修改方法 */
	void update(Seller seller);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Seller findOne(Serializable id);

	/** 查询全部 */
	List<Seller> findAll();

	/** 多条件分页查询 */
	PageResult findByPage(Seller seller, int page, int rows);

	void updateStatus(String sellerId, String status);
	/** 根据登录名查询商家 */
	Seller findName(String username);
}