package com.hust.yongpagani.miaosha.domain;

/**
 * @author Created by Divo
 * @date 2020/10/30
 */
public class User {
    private int id;
    private String name;

    public User(){}

    public User(int id,String name){
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
