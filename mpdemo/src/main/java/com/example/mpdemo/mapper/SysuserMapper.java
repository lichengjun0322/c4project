package com.example.mpdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mpdemo.entity.Sysuser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysuserMapper extends BaseMapper<Sysuser> {

    @Select("select * from sys_user")
    List<Sysuser> findAll();

    @Insert("insert into sys_user(username,`password`,email,phone) VALUES(#{username},#{password},#{email},#{phone});")
    int insert(Sysuser sysuser);

    int update(Sysuser sysuser);

    @Delete("delete from sys_user where id = #{id}")
    int deleteById(@Param("id") Integer id);
}
