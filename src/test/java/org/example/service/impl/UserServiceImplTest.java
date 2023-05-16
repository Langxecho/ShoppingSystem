package org.example.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void login() {
        UserServiceImpl jj = new UserServiceImpl();
        assertTrue(jj.login("root","123456"));
    }



    void registe() {
    }


    void buy() {
    }


    void getVip() {
    }


    void topUp() {
    }
}