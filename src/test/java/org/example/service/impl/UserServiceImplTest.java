package org.example.service.impl;

import org.example.dao.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;

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
    void flashReview() {
        UserServiceImpl a= new UserServiceImpl();
        ArrayList<JPanel> ar = a.flashReview(123);
        System.out.println(ar.size());
    }


    @Test
    void initbuyTable() {
        new UserServiceImpl().initbuyTable(29961670);
    }
}