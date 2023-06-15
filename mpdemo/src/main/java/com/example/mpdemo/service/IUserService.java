package com.example.mpdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mpdemo.controller.dto.SysuserDTO;
import com.example.mpdemo.entity.Sysuser;

public interface IUserService extends IService<Sysuser> {

    SysuserDTO login(SysuserDTO sysuserDTO);
}
