package org.example.service;

public interface UserService {
    boolean login(String name,String password);//登录
    boolean registe(String name,String password,String password2) throws Exception;//注册
    boolean buy(String goodid);//购买商品
    boolean getVip();//购买vip
    boolean topUp();//余额充值
    boolean changeName(String name);//修改用户名
    boolean review(String text);//添加评论
    boolean addFavourites(String goodid,String number);//添加购物车
    boolean cleanFavourites();//购物车内容购买



}
