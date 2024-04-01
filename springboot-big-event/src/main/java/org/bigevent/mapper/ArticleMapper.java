package org.bigevent.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bigevent.model.Article;

/**
* @author ZH280
* @description 针对表【article】的数据库操作Mapper
* @createDate 2024-04-01 13:06:31
* @Entity org.bigevent.model.Article
*/
@Mapper
public interface ArticleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

}
