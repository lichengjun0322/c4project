package com.example.mpdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mpdemo.entity.User;
import com.example.mpdemo.entity.Yuanqu;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface YuanquMapper {

    @Select("select * from yuanqu_info")
    List<Yuanqu> findall();

    @Insert("insert into yuanqu_info(id,name,location,fromYuanqu,modelimg,description) values" +
            "(#{id},#{name},#{location},#{fromYuanqu},#{modelimg},#{description})")
    int insert(Yuanqu yuanqu);

    @Update("update yuanqu_info set id=#{id},name=#{name}," +
            "location=#{location},fromYuanqu=#{fromYuanqu},modelimg=#{modelimg},description=#{description} where id=#{id}")
    int update(Yuanqu yuanqu);

    @Delete("delete from yuanqu_info where id = #{id}")
    Integer deleteById(@Param("id")Integer id);
}
