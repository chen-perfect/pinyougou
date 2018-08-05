package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.ItemCat;
import com.pinyougou.sellergoods.service.ItemCatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ItemCatController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:16
 * @version 1.0
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

	@Reference(timeout = 10000)
	private ItemCatService itemCatService;

	/** 根据父级id查询商品分类方法 */
	@GetMapping("/findItemCatByParentId")
	public List<ItemCat> findItemCatByParentId(Long parentId) {
		try {
			return itemCatService.findItemCatByParentId(parentId);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

}
