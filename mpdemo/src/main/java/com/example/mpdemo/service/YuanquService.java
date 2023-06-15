package com.example.mpdemo.service;

import com.example.mpdemo.entity.Yuanqu;
import com.example.mpdemo.mapper.YuanquMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YuanquService {
    @Autowired
    private YuanquMapper yuanquMapper;

    public int save(Yuanqu yuanqu) {
        Integer id = yuanqu.getId();
        if (id == null || id == 0) {
            return yuanquMapper.insert(yuanqu);
        } else {
            return yuanquMapper.update(yuanqu);
        }
    }
}
