package com.pinyougou.solr.util;

import com.alibaba.fastjson.JSON;
import com.pinyougou.mapper.ItemMapper;
import com.pinyougou.pojo.Item;
import com.pinyougou.solr.SolrItem;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SolrUtils
 *
 * @author lee.siu.wah
 * @version 1.0
 * <p>File Created at 2018-08-05<p>
 */
@Component
public class SolrUtils {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SolrTemplate solrTemplate;

    /** 把SKU表中的数据导入到Solr索引库 */
    public void importData(){

        // 创建Item对象
        Item item = new Item();
        // 状态码
        item.setStatus("1");
        // 查询tb_item表中的数据
        List<Item> itemList = itemMapper.select(item);

        System.out.println("======开始=======");
        // 需要把List<Item> 转化成 List<SolrItem>
        List<SolrItem> solrItemList = new ArrayList<>();

        for (Item item1 : itemList){
            System.out.println(item1.getId() + "\t" + item1.getTitle());
            SolrItem solrItem = new SolrItem();
            solrItem.setId(item1.getId());
            solrItem.setBrand(item1.getBrand());
            solrItem.setCategory(item1.getCategory());
            solrItem.setGoodsId(item1.getGoodsId());
            solrItem.setImage(item1.getImage());
            solrItem.setPrice(item1.getPrice());
            solrItem.setSeller(item1.getSeller());
            solrItem.setTitle(item1.getTitle());
            solrItem.setUpdateTime(item1.getUpdateTime());
            // 获取规格
            Map<String,String> specMap = JSON.parseObject(item1.getSpec(), Map.class);
            // 设置动态域
            solrItem.setSpecMap(specMap);

            solrItemList.add(solrItem);
        }

        UpdateResponse updateResponse = solrTemplate.saveBeans(solrItemList);
        if (updateResponse.getStatus() == 0){
            solrTemplate.commit();
        }else {
            solrTemplate.rollback();
        }

        System.out.println("======结束=======");
    }

    public static void main(String[] args){
        // 获取Spring容器
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取SolrUtils
        SolrUtils solrUtils = ac.getBean(SolrUtils.class);
        // 调用方法
        solrUtils.importData();
    }

}
