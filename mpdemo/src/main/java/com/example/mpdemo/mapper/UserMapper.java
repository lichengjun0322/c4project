package com.example.mpdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mpdemo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//用于操作用户表,MyBaits会根据Mapper注解，动态实现UserMapper接口（实现类），动态代理技术
//Spring会自动创建UserMapper接口实现类对应的实例
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //详细可以看mubatis官网
    //继承BaseMapper后很多方法不用自行定义，可ctrl + 右键打开查看内部函数

    //   查询用户，根据用户id查询信息   select * from user where id =
    @Select("select * from user where id = #{id}")
    List<User> selectById(int id);


//    @Select("select * from user")
//    public List<User> find();

//    @Insert("insert into user values ( #{id},#{username},#{password},#{birthday} ) ")
//    public int insert(User user);//用#调用函数的参数
//    //返回值表示插入了几条记录

    //   查询用户及其所有的订单
    @Select("select * from user")
    @Results(
            {
                    @Result(column = "id", property = "id"),//column是表里的字段，property是类里的字段
                    @Result(column = "username", property = "username"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "birthday", property = "birthday"),
                    @Result(column = "id", property = "orders", javaType = List.class, many = @Many(select = "com.example.mpdemo.mapper.OrderMapper.selectByUid")
                            //id传给后面selectByUid 作为参数
                    )
            }
    )
    List<User> selectAllUserAndOrders();
}
