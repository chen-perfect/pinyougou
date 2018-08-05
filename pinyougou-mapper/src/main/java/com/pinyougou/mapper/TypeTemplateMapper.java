package com.pinyougou.mapper;

import com.pinyougou.pojo.TypeTemplate;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * TypeTemplateMapper 数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:08
 * @version 1.0
 */
public interface TypeTemplateMapper extends Mapper<TypeTemplate>{

    /** 多条件查询类型模版 */
    List<TypeTemplate> findAll(@Param("typeTemplate") TypeTemplate typeTemplate);
}