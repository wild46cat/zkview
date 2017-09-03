package com.xueyou.zkview.service.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by wuxueyou on 2017/5/26.
 */
public class Student {
    private int id;
    private String name;

    private int age;
    private String attr;

    private Date createtime;
    private Date updatetime;


    public Student() {
    }

    public Student(int id) {
        this.id = id;
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(int id, String name, int age, String attr) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.attr = attr;
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


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
