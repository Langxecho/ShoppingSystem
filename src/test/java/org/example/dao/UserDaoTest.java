package org.example.dao;

import org.example.dao.impl.UserDaoImpl;
import org.example.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao userDao=new UserDaoImpl();

    @Test
    void insert() {
        User user = new User();
        try {
            int i = userDao.insert(user);
            Assertions.assertEquals(1,i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void delete(){

    }
}