package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SellerMapper;
import com.pinyougou.pojo.Seller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pojo.PageResult;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * SellerServiceImpl 服务接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:12
 * @version 1.0
 */
@Service(interfaceName = "com.pinyougou.sellergoods.service.SellerService")
@Transactional
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerMapper sellerMapper;

	/** 添加方法 */
	public void save(Seller seller){
		try {
			// 设置状态码
			seller.setStatus("0");
			// 设置申请时间
			seller.setCreateTime(new Date());
			sellerMapper.insertSelective(seller);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 修改方法 */
	public void update(Seller seller){
		try {
			sellerMapper.updateByPrimaryKeySelective(seller);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id删除 */
	public void delete(Serializable id){
		try {
			sellerMapper.deleteByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 批量删除 */
	public void deleteAll(Serializable[] ids){
		try {
			// 创建示范对象
			Example example = new Example(Seller.class);
			// 创建条件对象
			Example.Criteria criteria = example.createCriteria();
			// 创建In条件
			criteria.andIn("id", Arrays.asList(ids));
			// 根据示范对象删除
			sellerMapper.deleteByExample(example);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id查询 */
	public Seller findOne(Serializable id){
		try {
			return sellerMapper.selectByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 查询全部 */
	public List<Seller> findAll(){
		try {
			return sellerMapper.selectAll();
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 多条件分页查询 */
	public PageResult findByPage(Seller seller, int page, int rows){
		try {
			PageInfo<Seller> pageInfo = PageHelper.startPage(page, rows)
				.doSelectPageInfo(new ISelect() {
					@Override
					public void doSelect() {
						sellerMapper.findAll(seller);
					}
				});
			return new PageResult(pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**  审核商家(修改状态) */
	public void updateStatus(String sellerId, String status){
		try {
			// update tb_seller set status = ? where seller_id = ?
			Seller seller = new Seller();
			seller.setSellerId(sellerId);
			seller.setStatus(status);
			sellerMapper.updateByPrimaryKeySelective(seller);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

}