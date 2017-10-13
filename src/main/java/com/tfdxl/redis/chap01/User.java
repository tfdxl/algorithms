package com.tfdxl.redis.chap01;

/**
 * Created by tianfeng on 2017/10/13.
 */
public final class User {

    private String userName;
    private int age;
    private String password;

    public User(String userName, int age, String password) {
        this.userName = userName;
        this.age = age;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
