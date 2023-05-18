package org.example.dao;

import org.example.domain.User;

import java.sql.SQLException;

public interface UserDao {
    int insert(User user) throws SQLException;
    boolean insert(int id, String name, String password) throws SQLException;//用户注册将信息导入数据库
    boolean changeBalance(String username, int money) throws SQLException;//充值余额
    int check(int id,String password) throws Exception;
    int check(String name, String password) throws Exception;
    boolean check(String name) throws Exception;//注册时判断用户名是否重复
    int checkBalance(String username) throws Exception;//获取原来余额
}
