package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.Areas;
import java.util.List;
import java.io.Serializable;
/**
 * AreasService 服务接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:52
 * @version 1.0
 */
public interface AreasService {

	/** 添加方法 */
	void save(Areas areas);

	/** 修改方法 */
	void update(Areas areas);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Areas findOne(Serializable id);

	/** 查询全部 */
	List<Areas> findAll();

	/** 多条件分页查询 */
	List<Areas> findByPage(Areas areas, int page, int rows);

}