package com.zr.springboot.mapper;

import com.zr.springboot.entity.User;
import com.zr.springboot.entity.dto.UserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from shixun.user")
    List<User> findAll();

    @Insert("insert into shixun.user(user_name,user_phone,user_pw,user_sex,user_birth) values (#{user_name},#{user_phone},#{user_pw},#{user_sex},#{user_birth})")
    boolean insert(User user);

    @Insert("insert into shixun.user(user_phone,user_pw) values (#{user_phone},#{user_pw})")
    boolean insert(UserDTO userDTO);

    boolean update(User user);

    @Delete("delete from shixun.user where user_phone = #{user_phone}")
    int deleteByUserPhone(@Param("user_phone") String user_phone);
}
