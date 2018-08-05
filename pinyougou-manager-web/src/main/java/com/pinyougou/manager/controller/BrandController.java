package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Brand;
import com.pinyougou.sellergoods.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pojo.PageResult;

import java.util.List;
import java.util.Map;

/**
 * BrandController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:16
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
	public PageResult findByPage(Brand brand, Integer page, Integer rows) {
		try {
			// GET请求中文转码
			if (brand != null && StringUtils.isNoneBlank(brand.getName())){
				brand.setName(new String(brand.getName().getBytes("ISO-8859-1"), "UTF-8"));
			}
			// {'rows' : [{},{}], 'total' : 100}
			return brandService.findByPage(brand, page, rows);
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

	/** 查询所有的品牌 */
	@GetMapping("/findBrandList")
	public List<Map<String, Object>> findBrandList(){
		return brandService.findBrandByIdAndName();
	}

}
