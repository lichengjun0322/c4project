package com.example.mpdemo.controller;


import cn.hutool.core.util.StrUtil;
import com.example.mpdemo.commom.Constants;
import com.example.mpdemo.commom.Result;
import com.example.mpdemo.controller.dto.SysuserDTO;
import com.example.mpdemo.entity.Sysuser;
import com.example.mpdemo.mapper.SysuserMapper;
import com.example.mpdemo.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.AuthProvider;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/sysuser")
public class SysuserController {

    @Autowired
    private SysuserMapper sysuserMapper;

    @Autowired
    private SysuserService sysuserService;

    @GetMapping
    public List<Sysuser> findAll(){
        return sysuserMapper.findAll();
    }

    @PostMapping
    public Boolean save(@RequestBody Sysuser sysuser){//新增或者更新
        return sysuserService.saveUser(sysuser);
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return sysuserMapper.deleteById(id);
    }

    @PostMapping("/login")
    public Result login(@RequestBody SysuserDTO sysuserDTO) {
        String username = sysuserDTO.getUsername();
        String password = sysuserDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        SysuserDTO dto=sysuserService.login(sysuserDTO);
        return Result.success(dto);
    }

}
