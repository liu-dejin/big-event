package org.bigevent.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bigevent.model.User;

/**
* @author ZH280
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2024-04-01 13:07:46
* @Entity org.bigevent.model.User
*/
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserName(String username);
}
