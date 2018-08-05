package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pojo.PageResult;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * SpecificationServiceImpl 服务接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:12
 * @version 1.0
 */
@Service(interfaceName = "com.pinyougou.sellergoods.service.SpecificationService")
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private SpecificationMapper specificationMapper;
	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;


	/** 添加方法 */
	public void save(Specification specification){
		try {
		    // 往规格表添加数据
			specificationMapper.insertSelective(specification);
			// 往规格选项表添加数据
            specificationOptionMapper.save(specification);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 修改方法 */
	public void update(Specification specification){
		try {
			// 修改规格表中的数据(选择性修改) 判断specification对象中哪些属性有值 ，如果值有值就生成set
			specificationMapper.updateByPrimaryKeySelective(specification);

			// 根据规格id删除规格选项
			// delete from tb_specification_option where spec_id = ?
			// 创建SpecificationOption封装查询条件(等于号)
			SpecificationOption specificationOption = new SpecificationOption();
			specificationOption.setSpecId(specification.getId());
			specificationOptionMapper.delete(specificationOption);

			// 批量插入规格选项数据
			specificationOptionMapper.save(specification);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id删除 */
	public void delete(Serializable id){
		try {
			specificationMapper.deleteByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 批量删除 */
	public void deleteAll(Serializable[] ids){
		try {
			for (Serializable id : ids){
				// 根据规格id删除规格选项
				// delete from tb_specification_option where spec_id = ?
				// 创建SpecificationOption封装查询条件(等于号)
				SpecificationOption specificationOption = new SpecificationOption();
				specificationOption.setSpecId(Long.valueOf(id.toString()));
				specificationOptionMapper.delete(specificationOption);

				// 删除规格
				specificationMapper.deleteByPrimaryKey(id);
			}
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id查询 */
	public Specification findOne(Serializable id){
		try {
			return specificationMapper.selectByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 查询全部 */
	public List<Specification> findAll(){
		try {
			return specificationMapper.selectAll();
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 多条件分页查询 */
	public PageResult findByPage(Specification specification, int page, int rows){
		try {
			PageInfo<Specification> pageInfo = PageHelper.startPage(page, rows)
				.doSelectPageInfo(new ISelect() {
					@Override
					public void doSelect() {
						specificationMapper.findAll(specification);
					}
				});
			return new PageResult(pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据规格id查询规格选项 */
	public List<SpecificationOption> findSpecOptionBySpecId(Long id){
		try {
			// select * from tb_specification_option where spec_id = ?
			// 创建SpecificationOption封装查询条件(等于号)
			SpecificationOption specificationOption = new SpecificationOption();
			specificationOption.setSpecId(id);
			return specificationOptionMapper.select(specificationOption);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 查询规格数据(id与spec_name) */
	public List<Map<String,Object>> findSpecByIdAndName(){
		try {
			return specificationMapper.findSpecByIdAndName();
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}
}