package com.pinyougou.manager.controller;

import com.pinyougou.pojo.OrderItem;
import java.util.List;

import com.pinyougou.sellergoods.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * OrderItemController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

	@Autowired(required = false)
	private OrderItemService orderItemService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<OrderItem> save(OrderItem orderItem,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<OrderItem> orderItemList = orderItemService.findByPage(orderItem, page, rows);
			return orderItemList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public OrderItem findOne(Long id) {
		try {
			return orderItemService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody OrderItem orderItem) {
		try {
			orderItemService.save(orderItem);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody OrderItem orderItem) {
		try {
			orderItemService.update(orderItem);
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
			orderItemService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
