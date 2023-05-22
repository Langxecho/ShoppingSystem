package org.example.service.impl;

import org.example.dao.impl.AdminDaoImpl;
import org.example.dao.impl.UserDaoImpl;
import org.example.domain.Goods;
import org.example.domain.Review;
import org.example.domain.buy;
import org.example.frame.ReviewUI;
import org.example.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public int login(String name, String password){
        int num;
        boolean flag = false;
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            num = userDao.check(name,password);//检查登录是否成功并传递identity
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return num;
    }

    @Override
    //用户注册 返回true成功，返回false失败
    public boolean registe(String name, String password, String password2) throws Exception {
        Random rdm = new Random();
        int num = rdm.nextInt(899999) + 100000;//随机生成6位ID
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        boolean bl = userDaoImpl.check(name);//判断用户名是否注册
        if (bl == true) {
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

    //开通vip
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

    @Override
    //初始化用户购买界面的表格
    public JTable inittable() {
        String[] columnName = {"商品", "分类", "价格", "折扣", "库存"};
        ArrayList<Goods> array = new AdminDaoImpl().checkGoods();
        String tableValues[][] = new String[array.size()][5];
        for (int i = 0;i < array.size();i ++){
        Goods goods = array.get(i);
        tableValues[i][0] = String.valueOf(goods.getName());
        tableValues[i][1] = String.valueOf(goods.getCategory());
        tableValues[i][2] = String.valueOf(goods.getPrice());
        tableValues[i][3] = String.valueOf(goods.getDiscount());
        tableValues[i][4] = String.valueOf(goods.getStore());
        }
        JTable table = new JTable(tableValues,columnName);
        table = new JTable(tableValues, columnName) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };



        table.getTableHeader().setReorderingAllowed(false);   //不可整列移动
        table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
        return table;
    }

    @Override
    public JTable initbuyTable(int userid) {
        String[] columnName = {"商品", "购买时间", "购买数量","价格(元)"};
        ArrayList<buy> array = new UserDaoImpl().queryBuy();
        String tableValues[][] = new String[array.size()][4];
        for (int i = 0;i < array.size();i++){
            buy by = array.get(i);
            if (by.getUserid() == userid){
                tableValues[i][0] = new UserDaoImpl().getgoodName(by.getGoodid());
                tableValues[i][1] = by.getTime();
                tableValues[i][2] = String.valueOf(by.getCount());
                tableValues[i][3] = String.valueOf(by.getPay());
            }
        }
//        JTable table = new JTable(tableValues,columnName);
        JTable table = new JTable(tableValues, columnName) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);   //不可整列移动
        table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
        return table;
    }

    @Override
    public ArrayList flashReview(int goodid) {
       ArrayList<JPanel> arrayList = new ArrayList<>();
       ArrayList<Review> ar = new UserDaoImpl().queryCheckreview();
       for(int i = 0;i < ar.size();i++){
           Review re = ar.get(i);
           if (re.getGoodid() == goodid){
               JPanel panel = new ReviewUI("root").reviewarea(new UserDaoImpl().getuser(re.getUserid()),re.getContent(), re.getTime());
               arrayList.add(panel);
           }
        }
       return arrayList;
    }
}
