package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pojo.PageResult;

/**
 * TypeTemplateController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:16
 * @version 1.0
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Reference(timeout = 10000)
	private TypeTemplateService typeTemplateService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public PageResult findByPage(TypeTemplate typeTemplate,
								 Integer page, Integer rows) {
		try {
			/** GET请求中文转码 */
			if (typeTemplate != null && StringUtils
					.isNoneBlank(typeTemplate.getName())){
				typeTemplate.setName(new String(typeTemplate
						.getName().getBytes("ISO8859-1"),"UTF-8"));
			}
			return typeTemplateService.findByPage(typeTemplate, page, rows);
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
