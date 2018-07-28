package com.pinyougou.manager.controller;

import com.pinyougou.pojo.SpecificationOption;
import java.util.List;

import com.pinyougou.sellergoods.service.SpecificationOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * SpecificationOptionController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/specificationOption")
public class SpecificationOptionController {

	@Autowired(required = false)
	private SpecificationOptionService specificationOptionService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<SpecificationOption> save(SpecificationOption specificationOption,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<SpecificationOption> specificationOptionList = specificationOptionService.findByPage(specificationOption, page, rows);
			return specificationOptionList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public SpecificationOption findOne(Long id) {
		try {
			return specificationOptionService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody SpecificationOption specificationOption) {
		try {
			specificationOptionService.save(specificationOption);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody SpecificationOption specificationOption) {
		try {
			specificationOptionService.update(specificationOption);
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
			specificationOptionService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
