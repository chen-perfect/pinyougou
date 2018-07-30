package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Seller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.pinyougou.sellergoods.service.SellerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pojo.PageResult;

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

	@Reference(timeout = 10000)
	private SellerService sellerService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public PageResult save(Seller seller,
						   @RequestParam(value="page")Integer page,
						   @RequestParam(value="rows")Integer rows) throws UnsupportedEncodingException {
		/** GET请求中文转码 */
		if (seller != null && StringUtils.isNoneBlank(seller.getName())){
			seller.setName(new String(seller.getName()
					.getBytes("ISO8859-1"),"UTF-8"));
		}
		if (seller != null && StringUtils.isNoneBlank(seller.getNickName())){
			seller.setNickName(new String(seller.getNickName()
					.getBytes("ISO8859-1"),"UTF-8"));
		}
		try {
			return sellerService.findByPage(seller, page, rows);
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
	/**
	 * 修改商家状态
	 */
	@GetMapping("/updateStatus")
	public boolean updateStatus(String sellerId, String status){
		try {
			sellerService.updateStatus(sellerId, status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}
