package com.pinyougou.manager.controller;

import com.pinyougou.pojo.Seller;
import java.util.List;

import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * SellerController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Autowired(required = false)
	private SellerService sellerService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<Seller> save(Seller seller,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<Seller> sellerList = sellerService.findByPage(seller, page, rows);
			return sellerList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public Seller findOne(Long id) {
		try {
			return sellerService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody Seller seller) {
		try {
			sellerService.save(seller);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody Seller seller) {
		try {
			sellerService.update(seller);
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
			sellerService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
