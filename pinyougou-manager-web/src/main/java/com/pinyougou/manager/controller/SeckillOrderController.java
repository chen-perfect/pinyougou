package com.pinyougou.manager.controller;

import com.pinyougou.pojo.SeckillOrder;
import java.util.List;

import com.pinyougou.sellergoods.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * SeckillOrderController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/seckillOrder")
public class SeckillOrderController {

	@Autowired(required = false)
	private SeckillOrderService seckillOrderService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<SeckillOrder> save(SeckillOrder seckillOrder,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<SeckillOrder> seckillOrderList = seckillOrderService.findByPage(seckillOrder, page, rows);
			return seckillOrderList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public SeckillOrder findOne(Long id) {
		try {
			return seckillOrderService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody SeckillOrder seckillOrder) {
		try {
			seckillOrderService.save(seckillOrder);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody SeckillOrder seckillOrder) {
		try {
			seckillOrderService.update(seckillOrder);
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
			seckillOrderService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
