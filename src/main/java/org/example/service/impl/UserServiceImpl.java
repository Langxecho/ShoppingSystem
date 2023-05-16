package org.example.service.impl;

import org.example.dao.impl.UserDaoImpl;
import org.example.service.UserService;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/16 8:49
 */

public class UserServiceImpl implements UserService {
    @Override
    public boolean login(String name, String password){
        boolean flag = false;
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            int num = userDao.check(name,password);//检查登录是否成功并传递identity
            if (num == 0){

            }else if (num == 1 || num == 2){

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean registe(String name, String password, String password2) {
        return false;
    }

    @Override
    public boolean buy(String goodid) {
        return false;
    }

    @Override
    public boolean getVip() {
        return false;
    }

    @Override
    public boolean topUp() {
        return false;
    }

    @Override
    public boolean changeName(String name) {
        return false;
    }

    @Override
    public boolean review(String text) {
        return false;
    }

    @Override
    public boolean addFavourites(String goodid, String number) {
        return false;
    }

    @Override
    public boolean cleanFavourites() {
        return false;
    }
}
