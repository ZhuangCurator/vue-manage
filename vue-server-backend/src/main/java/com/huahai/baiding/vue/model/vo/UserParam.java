package com.baiding.vue.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: BaiDing
 * @Date: 2018/9/24 10:30
 * @Email: liujiabaiding@foxmail.com
 */
public class UserParam {
    private long id;
    private String email;
    private String name;
    private String password;
    private int sex;
    private int removed;
    private int age;
    private Date registerDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getRemoved() {
        return removed;
    }

    public void setRemoved(int removed) {
        this.removed = removed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRegisterDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(registerDate);
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
