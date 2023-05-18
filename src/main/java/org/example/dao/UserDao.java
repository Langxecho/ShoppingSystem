package org.example.dao;

import org.example.domain.User;

import java.sql.SQLException;

public interface UserDao {
    int insert(User user) throws SQLException;
    boolean insert(int id, String name, String password) throws SQLException;
    boolean changeBuy(double[] buy,int goodid,String name) throws Exception;
    int check(String name,String password) throws Exception;
    boolean check(String name) throws Exception;
    double[] checkBuy(int goodid,String name,int num) throws Exception;
    boolean changeName(String oldName,String newName) throws Exception;
    boolean changeBalance(String username, double money) throws SQLException;//充值余额
    boolean changeBalance2(String username) throws SQLException;//开通Vip扣除余额
    boolean changeVip(String username) throws SQLException;//开通Vip
    int check(int id,String password) throws Exception;
    double checkBalance(String username) throws SQLException;//获取余额
}
