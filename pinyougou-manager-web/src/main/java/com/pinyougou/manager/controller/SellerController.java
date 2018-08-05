package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Seller;
import com.pinyougou.sellergoods.service.SellerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pojo.PageResult;

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

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public PageResult findByPage(Seller seller, Integer page, Integer rows) {
		try {
			/** GET请求中文转码 */
			if (seller != null && StringUtils.isNoneBlank(seller.getName())){
				seller.setName(new String(seller.getName()
						.getBytes("ISO8859-1"),"UTF-8"));
			}
			if (seller != null && StringUtils.isNoneBlank(seller.getNickName())){
				seller.setNickName(new String(seller.getNickName()
						.getBytes("ISO8859-1"),"UTF-8"));
			}
			return sellerService.findByPage(seller, page, rows);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}


	/** 审核商家 */
	@GetMapping("/updateStatus")
	public boolean updateStatus(String sellerId, String status) {
		try {
			sellerService.updateStatus(sellerId, status);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
