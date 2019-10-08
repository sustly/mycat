package com.sustly.mycat.mapper;

import com.sustly.mycat.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

/**
 * @author liyue
 * @date 19-9-30 下午4:18
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(id, username, password, create_time, last_login_time) values (#{id}, #{username}, #{password}, #{createTime}, #{lastLoginTime})")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", keyColumn = "id", resultType = long.class, before = false)
    void insert(User user);

    @Select("select * from user where username = #{username}")
    User get(String username);
}
