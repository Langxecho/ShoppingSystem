package org.example.service;

import java.sql.SQLException;

public interface UserService {
    boolean login(String name,String password) throws Exception;//登录
    boolean registe(String name,String password,String password2) throws Exception;//注册
    boolean buy(int goodid,String name,int num);//购买商品
    boolean getVip(String username) throws SQLException;//购买vip
    boolean topUp(String username, double money) throws SQLException;//余额充值
    boolean changeName(String oldName,String newName) throws Exception;//修改用户名
    boolean review(String text);//添加评论
    boolean addFavourites(String goodid,String number);//添加购物车
    boolean cleanFavourites();//购物车内容购买



}
