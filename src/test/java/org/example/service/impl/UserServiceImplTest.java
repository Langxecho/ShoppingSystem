package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.buy(1234,"cao",3);
    }
}