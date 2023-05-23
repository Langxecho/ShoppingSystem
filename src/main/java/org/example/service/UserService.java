package org.example.service;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;

public interface UserService {
    int login(String name,String password) throws Exception;//登录
    boolean registe(String name,String password,String password2) throws Exception;//注册
    boolean buy(int goodid,String name,int num);//购买商品
    boolean getVip(String username) throws SQLException;//购买vip
    boolean topUp(String username, double money) throws SQLException;//余额充值
    boolean changeName(String oldName,String newName) throws Exception;//修改用户名
    boolean review(String text,int userid,int goodid) throws Exception;//添加评论
    boolean addFavourites(int goodid,int number,int userid)throws Exception;//添加购物车
    double cleanFavourites(int userid) throws Exception;//购物车内容购买
    JTable inittable();//加载用户购买界面的表格
    JTable initbuyTable(int userid);//加载购买信息界面的表格
    JTable initFavouritesTable(int userid);//加载购物车界面的表格
    ArrayList flashReview(int goodid);//刷新评论区方法
    void delFavourites(String user,int goodid,int buynumber);//三个形参分别是用户名,商品id和用户购买当前商品的数量,根据这三个信息找到购买表中唯一的一条数据,将其删除



}
