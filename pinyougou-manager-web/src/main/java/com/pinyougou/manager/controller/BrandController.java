package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Brand;
import java.util.List;

import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.*;
import pojo.PageResult;

/**
 * BrandController 控制器类
 * @author LEE.SIU.WAH
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

	@Reference(timeout = 10000)
	private BrandService brandService;
	/** 查询全部的品牌 */
	@GetMapping("/findAll")
	public List<Brand> findAll(){
		return brandService.findAll();
	}
	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public PageResult save(Brand brand,
			@RequestParam(value="page")Integer page,
			@RequestParam(value="rows")Integer rows) {
		try {
			PageResult pageResult = brandService.findByPage(brand, page, rows);
			return pageResult;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public Brand findOne(Long id) {
		try {
			return brandService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody Brand brand) {
		try {
			brandService.save(brand);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody Brand brand) {
		try {
			brandService.update(brand);
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
			brandService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
