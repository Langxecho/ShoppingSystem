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

    @Test
    void cleanFavourites() throws Exception {
        UserServiceImpl userService = new UserServiceImpl();
        userService.cleanFavourites(29961670);
    }

    @Test
    void addFavourites() throws Exception {
        UserServiceImpl userService = new UserServiceImpl();
        userService.addFavourites(123,10,29961670);
    }

    @Test
    void buy() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.buy(1,"nbshy",10);
    }
}