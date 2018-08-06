package com.pinyougou.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 商品搜索控制器
 *
 * @author lee.siu.wah
 * @version 1.0
 * <p>File Created at 2018-08-05<p>
 */
@RestController
public class ItemSearchController {

    /** 注入搜索服务接口 */
    @Reference(timeout = 30000)
    private ItemSearchService itemSearchService;

    /** 搜索方法 */
    @PostMapping("/Search")
    public Map<String,Object> search(@RequestBody Map<String, Object> params){
        return itemSearchService.search(params);
    }
}
