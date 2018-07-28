package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.GoodsDesc;
import java.util.List;
import java.io.Serializable;
/**
 * GoodsDescService 服务接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:52
 * @version 1.0
 */
public interface GoodsDescService {

	/** 添加方法 */
	void save(GoodsDesc goodsDesc);

	/** 修改方法 */
	void update(GoodsDesc goodsDesc);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	GoodsDesc findOne(Serializable id);

	/** 查询全部 */
	List<GoodsDesc> findAll();

	/** 多条件分页查询 */
	List<GoodsDesc> findByPage(GoodsDesc goodsDesc, int page, int rows);

}