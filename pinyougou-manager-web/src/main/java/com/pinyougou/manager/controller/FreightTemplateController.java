package com.pinyougou.manager.controller;

import com.pinyougou.pojo.FreightTemplate;

import java.util.List;

import com.pinyougou.sellergoods.service.FreightTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * FreightTemplateController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/freightTemplate")
public class FreightTemplateController {

	@Autowired(required = false)
	private FreightTemplateService freightTemplateService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<FreightTemplate> save(FreightTemplate freightTemplate,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<FreightTemplate> freightTemplateList = freightTemplateService.findByPage(freightTemplate, page, rows);
			return freightTemplateList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public FreightTemplate findOne(Long id) {
		try {
			return freightTemplateService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody FreightTemplate freightTemplate) {
		try {
			freightTemplateService.save(freightTemplate);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody FreightTemplate freightTemplate) {
		try {
			freightTemplateService.update(freightTemplate);
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
			freightTemplateService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
