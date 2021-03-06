package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Goods;
import com.pinyougou.sellergoods.service.GoodsService;
import org.springframework.web.bind.annotation.*;
import pojo.PageResult;

import java.util.List;
/**
 * GoodsController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:16
 * @version 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Reference(timeout = 10000)
	private GoodsService goodsService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public PageResult save(Goods goods,
						   @RequestParam(value="page", defaultValue="1")Integer page,
						   @RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			return goodsService.findByPage(goods, page, rows);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public Goods findOne(Long id) {
		try {
			return goodsService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody Goods goods) {
		try {
			goodsService.save(goods);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody Goods goods) {
		try {
			goodsService.update(goods);
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
			goodsService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
