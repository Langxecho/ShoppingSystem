package org.example.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void registe() throws Exception {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.registe("sbshysb", "123456", "123456");
    }
}