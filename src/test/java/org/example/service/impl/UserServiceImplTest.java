package org.example.service.impl;

import org.example.dao.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void changeName() throws Exception {
        UserServiceImpl userService = new UserServiceImpl();
        userService.changeName("cao","kao");
    }
}