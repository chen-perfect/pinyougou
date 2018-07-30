package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Seller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pojo.PageResult;

import java.util.List;

/**
 * SellerController 控制器类
 * @author LEE.SIU.WAHvm1
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference(timeout = 10000)
	private SellerService sellerService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public PageResult save(Seller seller, Integer page, Integer rows) {
		try {
			PageResult sellerList = sellerService.findByPage(seller, page, rows);
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
			/** 密码加密 */
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = passwordEncoder.encode(seller.getPassword());
			seller.setPassword(password);
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
