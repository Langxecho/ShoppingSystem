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
    //登录方法  true则登录成功，反之失败
    public boolean login(String name, String password){
        boolean flag = false;
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            int num = userDao.check(name,password);//检查登录是否成功并传递identity
            if (num == 0){//管理员登录
                flag = true;
            }else if (num == 1 || num == 2){//用户登录
                flag = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
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
    //购买商品，num是购买数量,true则购买成功否则失败
    public boolean buy(int goodid,String name,int num) {
        boolean flag = false;
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            double[] buy = userDao.checkBuy(goodid,name,num);//获取购买后的余额
            if (buy[1] < 0){
                System.err.println("用户余额不足");
            }else if (buy[0] < 0){
                System.err.println("商品数目不足");
            }else {
                flag = true;
                boolean flag2 = userDao.changeBuy(buy,goodid,name);//修改表中余额和数目
                if (!flag2){
                    System.err.println("修改失败！");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
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
