package org.example.service.impl;

import org.example.dao.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {


    void changeName() throws Exception {
        UserServiceImpl userService = new UserServiceImpl();
//        userService.changeName("cao","kao");
    }

    @Test
    void registe() throws Exception {
        UserServiceImpl userService = new UserServiceImpl();
        userService.registe("shy", "123456", "123456");
    }
}