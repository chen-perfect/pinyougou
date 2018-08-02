package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.pojo.ItemCat;
import com.pinyougou.mapper.ItemCatMapper;
import java.util.List;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.sellergoods.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.io.Serializable;
import java.util.Arrays;
/**
 * ItemCatServiceImpl 服务接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:52
 * @version 1.0
 */
@Service(interfaceName = "com.pinyougou.sellergoods.service.ItemCatService")
@Transactional
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;

	/** 添加方法 */
	@Override
	public void save(ItemCat itemCat){
		try {
			itemCatMapper.insertSelective(itemCat);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 修改方法 */
	@Override
	public void update(ItemCat itemCat){
		try {
			itemCatMapper.updateByPrimaryKeySelective(itemCat);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id删除 */
	@Override
	public void delete(Serializable id){
		try {
			itemCatMapper.deleteByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 批量删除 */
	@Override
	public void deleteAll(Serializable[] ids){
		try {
			// 创建示范对象
			Example example = new Example(ItemCat.class);
			// 创建条件对象
			Example.Criteria criteria = example.createCriteria();
			// 创建In条件
			criteria.andIn("id", Arrays.asList(ids));
			// 根据示范对象删除
			itemCatMapper.deleteByExample(example);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id查询 */
	@Override
	public ItemCat findOne(Serializable id){
		try {
			return itemCatMapper.selectByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 查询全部 */
	@Override
	public List<ItemCat> findAll(){
		try {
			return itemCatMapper.selectAll();
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 多条件分页查询 */
	@Override
	public List<ItemCat> findByPage(ItemCat itemCat, int page, int rows){
		try {
			PageInfo<ItemCat> pageInfo = PageHelper.startPage(page, rows)
				.doSelectPageInfo(new ISelect() {
					@Override
					public void doSelect() {
						itemCatMapper.selectAll();
					}
				});
			return pageInfo.getList();
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 根据父级id查询商品分类
	 *
	 * @param parentId
	 * @return
	 */
	@Override
	public List<ItemCat> findItemCatByParentId(Long parentId) {
		try {
			ItemCat itemCat = new ItemCat();
			itemCat.setParentId(parentId);
			return itemCatMapper.select(itemCat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}