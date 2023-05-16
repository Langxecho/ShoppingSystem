package org.example.dao.impl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class AdminDaoImplTest {

    @Test
    void checkGoods() {
        AdminDaoImpl a = new AdminDaoImpl();
        ArrayList array = a.checkGoods();
        for (Object go : array) {
            System.out.println(go.toString());
        }
    }
}