package com.pinyougou.manager.controller;

import com.pinyougou.pojo.Provinces;
import java.util.List;

import com.pinyougou.sellergoods.service.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * ProvincesController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/provinces")
public class ProvincesController {

	@Autowired(required = false)
	private ProvincesService provincesService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<Provinces> save(Provinces provinces,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<Provinces> provincesList = provincesService.findByPage(provinces, page, rows);
			return provincesList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public Provinces findOne(Long id) {
		try {
			return provincesService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody Provinces provinces) {
		try {
			provincesService.save(provinces);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody Provinces provinces) {
		try {
			provincesService.update(provinces);
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
			provincesService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
