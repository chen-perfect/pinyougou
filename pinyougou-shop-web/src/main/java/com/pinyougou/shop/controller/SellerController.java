package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Seller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * SellerController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:16
 * @version 1.0
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference(timeout = 10000)
	private SellerService sellerService;


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
			/** 密码加密对象 */
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			// 设置密码加密
			String password = passwordEncoder.encode(seller.getPassword());

			// 比较：BCrypt.checkpw("123456", "$2a$10$LCXn8dACTME9e0rWr3r9MOVTHjRqPg.nxmZwjWdsUwFRyM/u6cDYK");
			// passwordEncoder.matches()
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
