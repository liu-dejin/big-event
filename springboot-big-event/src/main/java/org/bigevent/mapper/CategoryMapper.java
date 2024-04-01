package org.bigevent.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bigevent.model.Category;

/**
* @author ZH280
* @description 针对表【category】的数据库操作Mapper
* @createDate 2024-04-01 13:07:30
* @Entity org.bigevent.model.Category
*/
@Mapper
public interface CategoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

}
