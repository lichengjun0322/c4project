package com.example.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

//@TableName("t_user")//可以表名和类名不同，使用这个注解告知程序去哪个表找数据
public class User {
    @TableId(type = IdType.AUTO)//告知程序哪个是主键，即使不传入该数据，仍可以回显，告知代码主键的值。
    private int id;
    private String username;
    private String password;
    private String birthday;

    //  描述用户的所有订单
    @TableField(exist = false)//exist是否为数据库表字段
    private List<Order> orders;//

//    alt + insert加下面的方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", orders=" + orders +
                '}';
    }
}
