package com.example.mpdemo.controller.dto;
/**
 * 接受前端登录请求的参数
 */
import lombok.Data;

@Data
public class SysuserDTO {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

