package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TypeTemplate;
import pojo.PageResult;

import java.util.List;
import java.io.Serializable;
import java.util.Map;

/**
 * TypeTemplateService 服务接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:12
 * @version 1.0
 */
public interface TypeTemplateService {

	/** 添加方法 */
	void save(TypeTemplate typeTemplate);

	/** 修改方法 */
	void update(TypeTemplate typeTemplate);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	TypeTemplate findOne(Serializable id);

	/** 查询全部 */
	List<TypeTemplate> findAll();

	/** 多条件分页查询 */
	PageResult findByPage(TypeTemplate typeTemplate, int page, int rows);

	/** 根据类型模版id查询规格及规格选项 */
    List<Map> findSpecByTemplateId(Long id);
}