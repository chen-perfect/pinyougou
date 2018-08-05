package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.*;
import com.pinyougou.pojo.Goods;
import com.pinyougou.pojo.Item;
import com.pinyougou.pojo.ItemCat;
import com.pinyougou.sellergoods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pojo.PageResult;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * GoodsServiceImpl 服务接口实现类
 * @date 2018-07-27 00:08:48
 * @version 1.0
 */
@Service(interfaceName = "com.pinyougou.sellergoods.service.GoodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsDescMapper goodsDescMapper;
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private SellerMapper sellerMapper;
	@Autowired
	private ItemMapper itemMapper;

	/** 添加方法 */
	public void save(Goods goods){
		try {
			// 往tb_goods表插入数据(SPU)
			// 设置审核状态码(未审核)
			goods.setAuditStatus("0");
			goodsMapper.insertSelective(goods);

			// 往tb_goods_desc表插入数据
			// 设置主键id
			goods.getGoodsDesc().setGoodsId(goods.getId());
			goodsDescMapper.insertSelective(goods.getGoodsDesc());

			// 判断是否启用规格
			if ("1".equals(goods.getIsEnableSpec())) {
				// 往tb_item表循环插入数据
				for (Item item : goods.getItems()) {
					// {spec:{}, price:0, num:9999, status:'0', isDefault:'0'}
					// {"网络":"联通4G","机身内存":"64G"}
					// 设置SKU商品标题
					StringBuilder title = new StringBuilder();
					title.append(goods.getGoodsName());
					// 获取规格选项Map集合 {} JSON.parseObject [{}] JSON.parseArray
					Map specMap = JSON.parseObject(item.getSpec());
					for (Object value : specMap.values()) {
						title.append(" " + value.toString());
					}
					item.setTitle(title.toString());
					/** 设置SKU商品其它属性 */
					setItemInfo(goods, item);
					// SKU表插入数据
					itemMapper.insertSelective(item);
				}
			}else{ // SPU就是SKU (往tb_item插入一条数据)
				// {spec:{}, price:0, num:9999, status:'0', isDefault:'0'}
				/** 创建SKU具体商品对象 */
				Item item = new Item();
				/** 设置SKU商品的标题 */
				item.setTitle(goods.getGoodsName());
				/** 设置SKU商品的价格 */
				item.setPrice(goods.getPrice());
				/** 设置SKU商品库存数据 */
				item.setNum(9999);
				/** 设置SKU商品启用状态 */
				item.setStatus("1");
				/** 设置是否默认*/
				item.setIsDefault("1");
				/** 设置规格选项 */
				item.setSpec("{}");
				/** 设置SKU商品其它属性 */
				setItemInfo(goods, item);
				// SKU表插入数据
				itemMapper.insertSelective(item);
			}

		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	// 设置SKU商品的信息
	private void setItemInfo(Goods goods, Item item) {

		// [{"color":"金色","url":"http://image.pinyougou.com/jd/wKgMg1qtKEOATL9nAAFti6upbx4132.jpg"},
		// {"color":"深空灰色","url":"http://image.pinyougou.com/jd/wKgMg1qtKHmAFxj7AAFZsBqChgk725.jpg"},
		// {"color":"银色","url":"http://image.pinyougou.com/jd/wKgMg1qtKJyAHQ9sAAFuOBobu-A759.jpg"}]
		List<Map> mapList = JSON.parseArray(goods.getGoodsDesc().getItemImages(), Map.class);
		if (mapList != null && mapList.size() > 0) {
			// 商品图片
			item.setImage(mapList.get(0).get("url").toString());
		}
		// 设置商品的三级分类
		item.setCategoryid(goods.getCategory3Id());
		// 创建时间
		item.setCreateTime(new Date());
		// 更新时间
		item.setUpdateTime(item.getCreateTime());
		// 设置关联的SPU的id
		item.setGoodsId(goods.getId());
		// 设置商家的id
		item.setSellerId(goods.getSellerId());
		// 设置三级分类的名称
		item.setCategory(itemCatMapper
				.selectByPrimaryKey(goods.getCategory3Id()).getName());
		// 设置品牌的名称
		item.setBrand(brandMapper.selectByPrimaryKey(goods.getBrandId()).getName());
		// 设置商家的名称
		item.setSeller(sellerMapper.selectByPrimaryKey(goods.getSellerId()).getNickName());
	}


	/** 修改方法 */
	public void update(Goods goods){
		try {
			goodsMapper.updateByPrimaryKeySelective(goods);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id删除 */
	public void delete(Serializable id){
		try {
			goodsMapper.deleteByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 批量删除 */
	public void deleteAll(Serializable[] ids){
		try {
			// 创建示范对象
			Example example = new Example(Goods.class);
			// 创建条件对象
			Example.Criteria criteria = example.createCriteria();
			// 创建In条件
			criteria.andIn("id", Arrays.asList(ids));
			// 根据示范对象删除
			goodsMapper.deleteByExample(example);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id查询 */
	public Goods findOne(Serializable id){
		try {
			return goodsMapper.selectByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 查询全部 */
	public List<Goods> findAll(){
		try {
			return goodsMapper.selectAll();
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 多条件分页查询 */
	public PageResult findByPage(Goods goods, int page, int rows){
		try {
			PageInfo<Map<String,Object>> pageInfo = PageHelper.startPage(page, rows)
				.doSelectPageInfo(new ISelect() {
					@Override
					public void doSelect() {
						goodsMapper.findAll(goods);
					}
				});
			for (Map<String, Object> map : pageInfo.getList()) {
				// 查询一级分类
				ItemCat itemCat1 = itemCatMapper.selectByPrimaryKey(map.get("category1Id"));
				map.put("category1Name",itemCat1 != null ? itemCat1.getName() : "");

				// 查询二级分类
				ItemCat itemCat2 = itemCatMapper.selectByPrimaryKey(map.get("category2Id"));
				map.put("category2Name",itemCat2 != null ? itemCat2.getName() : "");

				// 查询三级分类
				ItemCat itemCat3 = itemCatMapper.selectByPrimaryKey(map.get("category3Id"));
				map.put("category3Name",itemCat3 != null ? itemCat3.getName() : "");
			}
			return new PageResult(pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

}