package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.support.Parameter;
import com.pinyougou.pojo.ItemCat;
import java.util.List;

import com.pinyougou.sellergoods.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * ItemCatController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

	@Reference(timeout = 10000)
	private ItemCatService itemCatService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<ItemCat> save(ItemCat itemCat,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<ItemCat> itemCatList = itemCatService.findByPage(itemCat, page, rows);
			return itemCatList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public ItemCat findOne(Long id) {
		try {
			return itemCatService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody ItemCat itemCat) {
		try {
			itemCatService.save(itemCat);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody ItemCat itemCat) {
		try {
			itemCatService.update(itemCat);
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
			itemCatService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据父级id查询商品分类
	 * @param parentId
	 * @return
	 */
	@GetMapping("/findItemCatByParentId")
	public List<ItemCat> findItemCatByParentId(Long parentId) {
		return itemCatService.findItemCatByParentId(parentId);
	}

}
