package com.example.mpdemo.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
public class Yuanqu {
    @TableId(type = IdType.AUTO)//告知程序哪个是主键，即使不传入该数据，仍可以回显，告知代码主键的值。
    private int id;
    private String name;
    private String location;
    private String fromYuanqu;
    private String modelimg;

    @Override
    public String toString() {
        return "Yuanqu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", fromYuanqu='" + fromYuanqu + '\'' +
                ", modelimg='" + modelimg + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public String getModelimg() {
        return modelimg;
    }

    public void setModelimg(String modelimg) {
        this.modelimg = modelimg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFromYuanqu() {
        return fromYuanqu;
    }

    public void setFromYuanqu(String fromYuanqu) {
        this.fromYuanqu = fromYuanqu;
    }

}
