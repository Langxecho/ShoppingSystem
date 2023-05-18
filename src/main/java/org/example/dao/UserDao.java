package org.example.dao;

import org.example.domain.User;

import java.sql.SQLException;

public interface UserDao {
    int insert(User user) throws SQLException;
    boolean insert(int id, String name, String password);
    boolean changeBuy(double[] buy,int goodid,String name) throws Exception;
    int check(String name,String password) throws Exception;
    boolean check(String name) throws Exception;
    double[] checkBuy(int goodid,String name,int num) throws Exception;
    boolean changeName(String oldName,String newName) throws Exception;
}
