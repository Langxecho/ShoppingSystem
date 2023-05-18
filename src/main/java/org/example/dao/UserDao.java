package org.example.dao;

import org.example.domain.User;

import java.sql.SQLException;

public interface UserDao {
    int insert(User user) throws SQLException;
    boolean insert(int id, String name, String password);
    int check(int id,String password) throws Exception;
    boolean check(String name) throws Exception;
}
