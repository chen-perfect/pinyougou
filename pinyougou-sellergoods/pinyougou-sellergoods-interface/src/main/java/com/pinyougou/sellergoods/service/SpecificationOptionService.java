package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.SpecificationOption;
import java.util.List;
import java.io.Serializable;
/**
 * SpecificationOptionService 服务接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:52
 * @version 1.0
 */
public interface SpecificationOptionService {

	/** 添加方法 */
	void save(SpecificationOption specificationOption);

	/** 修改方法 */
	void update(SpecificationOption specificationOption);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	SpecificationOption findOne(Serializable id);

	/** 查询全部 */
	List<SpecificationOption> findAll();

	/** 多条件分页查询 */
	List<SpecificationOption> findByPage(SpecificationOption specificationOption, int page, int rows);

}