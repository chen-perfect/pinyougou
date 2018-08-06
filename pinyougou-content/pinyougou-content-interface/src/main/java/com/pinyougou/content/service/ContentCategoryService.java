package com.pinyougou.content.service;

import com.pinyougou.pojo.ContentCategory;
import pojo.PageResult;

import java.io.Serializable;
import java.util.List;
/**
 * ContentCategoryService 服务接口
 * @date 2018-08-03 15:11:02
 * @version 1.0
 */
public interface ContentCategoryService {

	/** 添加方法 */
	void save(ContentCategory contentCategory);

	/** 修改方法 */
	void update(ContentCategory contentCategory);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	ContentCategory findOne(Serializable id);

	/** 查询全部 */
	List<ContentCategory> findAll();

	/** 多条件分页查询 */
	PageResult findByPage(ContentCategory contentCategory, int page, int rows);

}