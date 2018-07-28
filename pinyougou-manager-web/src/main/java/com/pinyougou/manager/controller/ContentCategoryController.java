package com.pinyougou.manager.controller;

import com.pinyougou.pojo.ContentCategory;
import java.util.List;

import com.pinyougou.sellergoods.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * ContentCategoryController 控制器类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-26 23:20:59
 * @version 1.0
 */
@RestController
@RequestMapping("/contentCategory")
public class ContentCategoryController {

	@Autowired(required = false)
	private ContentCategoryService contentCategoryService;

	/** 多条件分页查询方法 */
	@GetMapping("/findByPage")
	public List<ContentCategory> save(ContentCategory contentCategory,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value="rows", defaultValue="10")Integer rows) {
		try {
			List<ContentCategory> contentCategoryList = contentCategoryService.findByPage(contentCategory, page, rows);
			return contentCategoryList;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 根据主键id查询方法 */
	@GetMapping("/findOne")
	public ContentCategory findOne(Long id) {
		try {
			return contentCategoryService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/** 添加方法 */
	@PostMapping("/save")
	public boolean save(@RequestBody ContentCategory contentCategory) {
		try {
			contentCategoryService.save(contentCategory);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/** 修改方法 */
	@PostMapping("/update")
	public boolean update(@RequestBody ContentCategory contentCategory) {
		try {
			contentCategoryService.update(contentCategory);
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
			contentCategoryService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
