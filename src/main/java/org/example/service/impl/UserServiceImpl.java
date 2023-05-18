package org.example.service.impl;

import org.example.dao.impl.UserDaoImpl;
import org.example.service.UserService;

import java.sql.SQLException;
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
        int num = rdm.nextInt(899999) + 100000;//随机生成6位ID
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        boolean bl = userDaoImpl.check(name);//判断用户名是否注册
        if (bl = true) {
            System.out.println("用户名已存在");
        } else {
            if (Objects.equals(password, password2)) {
                userDaoImpl.insert(num, name, password);
                System.out.println("注册信息导入成功");
            } else {
                System.out.println("密码不一致");
            }
        }
        return bl;
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
            }if (buy[0] < 0){
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
    public boolean getVip(String username) throws SQLException {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        boolean bl = userDaoImpl.changeVip(username);
        if (bl == true) {
            System.out.println("Vip开通成功");
        } else {
            System.out.println("余额不足");
        }
        return bl;
    }

    @Override
    //余额充值
    public boolean topUp(String username, double money) throws SQLException {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        userDaoImpl.changeBalance(username, money);
        return true;
    }

    @Override
    public boolean changeName(String oldName,String newName) throws Exception{
        UserDaoImpl userDao = new UserDaoImpl();
        boolean flag = userDao.changeName(oldName,newName);
        if (flag){
            System.out.println("修改成功");
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean review(String text,int userid,int goodid) throws Exception{
        UserDaoImpl userDao = new UserDaoImpl();
        boolean flag = userDao.insertReview(text,userid,goodid);
        if (flag){
            System.out.println("评论成功");
            return true;
        }else {
            System.out.println("评论失败");
            return false;
        }

    }

    @Override
    //ture则添加成功，否则失败
    public boolean addFavourites(int goodid, int number,int userid) throws Exception{
        UserDaoImpl userDao = new UserDaoImpl();
        boolean flag =userDao.insertFavourites(goodid,number,userid);
        return flag;
    }

    @Override
    public boolean cleanFavourites() {
        return false;
    }
}
