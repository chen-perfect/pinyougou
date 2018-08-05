package com.pinyougou.mapper;

import com.pinyougou.pojo.Specification;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * SpecificationMapper 数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2018-07-25 16:10:08
 * @version 1.0
 */
public interface SpecificationMapper extends Mapper<Specification>{


    /** 多条件查询规格 */
    List<Specification> findAll(@Param("specification") Specification specification);

    /** 查询规格数据(id与spec_name) */
    @Select("select id, spec_name as text from tb_specification order by id asc")
    List<Map<String,Object>> findSpecByIdAndName();
}