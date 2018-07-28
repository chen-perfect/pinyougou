package com.pinyougou.manager.controller;

import com.pinyougou.pojo.GoodsDesc;
import java.util.List;

import com.pinyougou.sellergoods.service.GoodsDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * GoodsDescController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/goodsDesc")
public class GoodsDescController {

	@Autowired(required = false)
	private GoodsDescService goodsDescService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<GoodsDesc> save(GoodsDesc goodsDesc,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<GoodsDesc> goodsDescList = goodsDescService.findByPage(goodsDesc, page, rows);
			return goodsDescList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public GoodsDesc findOne(Long id) {
		try {
			return goodsDescService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody GoodsDesc goodsDesc) {
		try {
			goodsDescService.save(goodsDesc);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody GoodsDesc goodsDesc) {
		try {
			goodsDescService.update(goodsDesc);
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
			goodsDescService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
