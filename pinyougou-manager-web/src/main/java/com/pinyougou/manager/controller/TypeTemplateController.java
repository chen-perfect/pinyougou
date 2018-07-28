package com.pinyougou.manager.controller;

import com.pinyougou.pojo.TypeTemplate;
import java.util.List;

import com.pinyougou.sellergoods.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * TypeTemplateController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Autowired(required = false)
	private TypeTemplateService typeTemplateService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<TypeTemplate> save(TypeTemplate typeTemplate,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<TypeTemplate> typeTemplateList = typeTemplateService.findByPage(typeTemplate, page, rows);
			return typeTemplateList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public TypeTemplate findOne(Long id) {
		try {
			return typeTemplateService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody TypeTemplate typeTemplate) {
		try {
			typeTemplateService.save(typeTemplate);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody TypeTemplate typeTemplate) {
		try {
			typeTemplateService.update(typeTemplate);
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
			typeTemplateService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
