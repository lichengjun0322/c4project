package com.example.mpdemo.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mpdemo.commom.Constants;
import com.example.mpdemo.controller.dto.SysuserDTO;
import com.example.mpdemo.entity.Sysuser;
import com.example.mpdemo.exception.ServiceException;
import com.example.mpdemo.mapper.SysuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysuserService extends ServiceImpl<SysuserMapper,Sysuser> {

    public Boolean saveUser(Sysuser sysuser) {
//        if(sysuser.getId()==null){
//            return save(sysuser);
//        }else{
//            return updateById(sysuser);
//        }
        return saveOrUpdate(sysuser);
    }

    public SysuserDTO login(SysuserDTO sysuserDTO) {
        QueryWrapper<Sysuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", sysuserDTO.getUsername());
        queryWrapper.eq("password", sysuserDTO.getPassword());
        Sysuser one;
        // 处理异常情况
        try {
            one = getOne(queryWrapper);

        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(one != null){
            BeanUtil.copyProperties(one,sysuserDTO,true);
            return sysuserDTO;
        }
        else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

//    @Autowired
//    private SysuserMapper sysuserMapper;
//
//    public int save(Sysuser sysuser) {
//        if(sysuser.getId() == null){
//            return sysuserMapper.insert(sysuser);
//        }else{
//            return sysuserMapper.update(sysuser);
//        }
//    }

}