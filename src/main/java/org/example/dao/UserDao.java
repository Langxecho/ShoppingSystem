package org.example.dao;

import org.example.domain.User;

import java.sql.SQLException;

public interface UserDao {
    int insert(User user) throws SQLException;

}
