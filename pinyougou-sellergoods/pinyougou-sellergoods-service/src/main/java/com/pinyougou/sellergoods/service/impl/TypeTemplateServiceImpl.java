package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.mapper.TypeTemplateMapper;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.pojo.TypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pojo.PageResult;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * TypeTemplateServiceImpl 服务接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:12
 * @version 1.0
 */
@Service(interfaceName = "com.pinyougou.sellergoods.service.TypeTemplateService")
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {

	@Autowired
	private TypeTemplateMapper typeTemplateMapper;
	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;

	/** 添加方法 */
	public void save(TypeTemplate typeTemplate){
		try {
			typeTemplateMapper.insertSelective(typeTemplate);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 修改方法 */
	public void update(TypeTemplate typeTemplate){
		try {
			typeTemplateMapper.updateByPrimaryKeySelective(typeTemplate);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id删除 */
	public void delete(Serializable id){
		try {
			typeTemplateMapper.deleteByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 批量删除 */
	public void deleteAll(Serializable[] ids){
		try {
			// 创建示范对象
			Example example = new Example(TypeTemplate.class);
			// 创建条件对象
			Example.Criteria criteria = example.createCriteria();
			// 创建In条件
			criteria.andIn("id", Arrays.asList(ids));
			// 根据示范对象删除
			typeTemplateMapper.deleteByExample(example);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id查询 */
	public TypeTemplate findOne(Serializable id){
		try {
			return typeTemplateMapper.selectByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 查询全部 */
	public List<TypeTemplate> findAll(){
		try {
			return typeTemplateMapper.selectAll();
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 多条件分页查询 */
	public PageResult findByPage(TypeTemplate typeTemplate, int page, int rows){
		try {
			PageInfo<TypeTemplate> pageInfo = PageHelper.startPage(page, rows)
				.doSelectPageInfo(new ISelect() {
					@Override
					public void doSelect() {
						typeTemplateMapper.findAll(typeTemplate);
					}
				});
			return new PageResult(pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据类型模版id查询规格及规格选项 */
	public List<Map> findSpecByTemplateId(Long id){
		try {
			// 根据id查询类型模版对象
			TypeTemplate typeTemplate = findOne(id);
			//spec_ids [{"id":27,"text":"网络"},{"id":32,"text":"机身内存"}]
			// 把spec_ids 转化成List<Map>
			List<Map> specList = JSON.parseArray(typeTemplate.getSpecIds(), Map.class);
			// 迭代
			for (Map map : specList){
				// 取id
				Long specId = Long.valueOf(map.get("id").toString());
				// select * from tb_specification_option where spec_id = ?
				// 创建规格选项对象封装查询条件
				SpecificationOption so = new SpecificationOption();
				so.setSpecId(specId);
				List<SpecificationOption> soList = specificationOptionMapper.select(so);

				map.put("options", soList);
			}
			return specList;

		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

}