package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * TypeTemplateController 控制器类
 * @date 2018-07-31 15:48:09
 * @version 1.0
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Reference(timeout = 10000)
	private TypeTemplateService typeTemplateService;

	/** 查询全部方法 */
	@GetMapping("/findAll")
	public List<TypeTemplate> findAll() {
		return typeTemplateService.findAll();
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

	/** 根据类型模版id查询规格及规格选项 */
	@GetMapping("/findSpecByTemplateId")
	public List<Map> findSpecByTemplateId(Long id){
		try {
			return typeTemplateService.findSpecByTemplateId(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

}
