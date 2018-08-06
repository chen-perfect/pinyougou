package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.search.service.ItemSearchService;
import com.pinyougou.solr.SolrItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: pinyougou
 * @description:
 * @author: Mr.Chen
 * @create: 2018-08-06 10:53
 **/
@Service
public class ItemSearchServiceImpl implements ItemSearchService {
    @Autowired
    private SolrTemplate solrTemplate;
    /**
     * 搜索方法
     *
     * @param params
     */
    @Override
    public Map<String, Object> search(Map<String, Object> params) {
        /** 创建Map集合封装返回数据 **/
        Map<String, Object> data = new HashMap<>();
        /** 创建查询对象 */
        SimpleQuery simpleQuery = new SimpleQuery("*:*");
        /** 获取检索关键字 */
        String keywords = (String) params.get("keywords");
        /** 判断检索关键字 */
        if (StringUtils.isNoneBlank(keywords)) {
            /** 创建查询条件 */
            Criteria criteria = new Criteria("keywords").is(keywords);
            /** 添加查询条件 */
            simpleQuery.addCriteria(criteria);
        }
        /** 分页检索 */
        ScoredPage<SolrItem> scoredPage = solrTemplate
                .queryForPage(simpleQuery, SolrItem.class);
        /** 获取内容 */
        data.put("rows", scoredPage.getContent());
        return data;
    }
}
