package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pojo.PageResult;

import java.util.List;
import java.util.Map;

/**
 * SpecificationController 控制器类
 * @date 2018-07-25 16:10:16
 * @version 1.0
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {

	@Reference(timeout = 10000)
	private SpecificationService specificationService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public PageResult findByPage(Specification specification,
								 Integer page, Integer rows) {
		try {
			/** GET请求中文转码 */
			if (specification != null && StringUtils
					.isNoneBlank(specification.getSpecName())){
				specification.setSpecName(new String(specification
						.getSpecName().getBytes("ISO8859-1"),"UTF-8"));
			}
			return specificationService.findByPage(specification, page, rows);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据规格id查询规格选项 */
	@GetMapping("/findOne")
	public List<SpecificationOption> findOne(Long id) {
		try {
			return specificationService.findSpecOptionBySpecId(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody Specification specification) {
		try {
			specificationService.save(specification);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody Specification specification) {
		try {
			specificationService.update(specification);
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
			specificationService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 查询规格数据 */
	@GetMapping("/findSpecList")
	public List<Map<String,Object>> findSpecList(){
		return specificationService.findSpecByIdAndName();
	}

}
