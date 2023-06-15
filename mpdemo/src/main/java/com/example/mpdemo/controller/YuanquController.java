package com.example.mpdemo.controller;

import com.example.mpdemo.entity.Yuanqu;
import com.example.mpdemo.mapper.UserMapper;
import com.example.mpdemo.mapper.YuanquMapper;
import com.example.mpdemo.service.YuanquService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class YuanquController {

    @Autowired
    YuanquMapper yuanquMapper;
    @Autowired
    private YuanquService yuanquService;

    @PostMapping("/insertyuanqu")
    public Integer save(@RequestBody Yuanqu yuanqu) {
        //新增或者更新
        return yuanquService.save(yuanqu);
    }

    @GetMapping("/yuanqu")
    public List<Yuanqu> find() {
        return yuanquMapper.findall();
    }

    @DeleteMapping("yuanqu/{id}")
    public Integer delete(@PathVariable Integer id){
        return yuanquMapper.deleteById(id);
    }
}
