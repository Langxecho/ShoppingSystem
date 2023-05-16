package org.example.service.impl;

import org.example.dao.impl.UserDaoImpl;
import org.example.service.UserService;

import java.util.Objects;
import java.util.Random;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/16 8:49
 */

public class UserServiceImpl implements UserService {
    @Override
    public boolean login(String name, String password) {
        return false;
    }

    @Override
    //用户注册 返回true成功，返回false失败
    public boolean registe(String name, String password, String password2) throws Exception {
        Random rdm = new Random();
        int num = rdm.nextInt(899999) + 100000;
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        boolean bl = userDaoImpl.check(name);
        boolean bl2 = true;
        if (bl) {
            System.out.println("用户名已存在");
            bl2 = false;
        } else {
            if (Objects.equals(password, password2)) {
                userDaoImpl.insert(num, name, password);
                System.out.println("注册信息导入成功");
            } else {
                System.out.println("密码不一致");
                bl2 = false;
            }
        }
        return bl2;
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
