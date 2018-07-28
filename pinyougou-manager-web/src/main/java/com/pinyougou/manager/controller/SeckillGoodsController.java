package com.pinyougou.manager.controller;

import com.pinyougou.pojo.SeckillGoods;
import java.util.List;

import com.pinyougou.sellergoods.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * SeckillGoodsController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/seckillGoods")
public class SeckillGoodsController {

	@Autowired(required = false)
	private SeckillGoodsService seckillGoodsService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<SeckillGoods> save(SeckillGoods seckillGoods,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<SeckillGoods> seckillGoodsList = seckillGoodsService.findByPage(seckillGoods, page, rows);
			return seckillGoodsList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public SeckillGoods findOne(Long id) {
		try {
			return seckillGoodsService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody SeckillGoods seckillGoods) {
		try {
			seckillGoodsService.save(seckillGoods);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody SeckillGoods seckillGoods) {
		try {
			seckillGoodsService.update(seckillGoods);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 删除方法 */
	@GetMapping("/delete")
	public boolean delete(Long[] ids) {
		try {
			seckillGoodsService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
