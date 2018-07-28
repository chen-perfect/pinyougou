package com.pinyougou.manager.controller;

import com.pinyougou.pojo.Cities;
import java.util.List;

import com.pinyougou.sellergoods.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * CitiesController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/cities")
public class CitiesController {

	@Autowired(required = false)
	private CitiesService citiesService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<Cities> save(Cities cities,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<Cities> citiesList = citiesService.findByPage(cities, page, rows);
			return citiesList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public Cities findOne(Long id) {
		try {
			return citiesService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody Cities cities) {
		try {
			citiesService.save(cities);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody Cities cities) {
		try {
			citiesService.update(cities);
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
			citiesService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
