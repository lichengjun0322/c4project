package com.example.mpdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpdemo.entity.User;
import com.example.mpdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/user/findbyid")
    public List<User> findbyid(int id){
        return userMapper.selectById(id);
    }

    @GetMapping("/user/findAll")
    public List<User> find(){
        return userMapper.selectAllUserAndOrders();
    }
//
    //  条件查询
    @GetMapping("/user/find")
    public List<User> findByCond(){
        QueryWrapper<User> queryWrapper = new QueryWrapper();//queryWrapper条件查找
        queryWrapper.eq("username","zhangsan");
        return userMapper.selectList(queryWrapper);
    }

    //  分页查询
    @GetMapping("/user/findByPage")
    public IPage findByPage(){
        //设置起始值及每页条数
        Page<User> page = new Page<>(0,2);
        IPage iPage = userMapper.selectPage(page,null);
        return iPage;
    }


    //  插入数据
    @PostMapping("/user")
    public String save(User user){
        int r= userMapper.insert(user);
        if(r > 0){
            return "插入成功";
        }else{
            return "插入失败";
        }
    }
}
