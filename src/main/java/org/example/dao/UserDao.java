package org.example.dao;

import org.example.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {
    int insert(User user) throws SQLException;
    boolean insert(int id, String name, String password) throws SQLException;
    boolean insertReview(String text,int userid,int goodid) throws Exception;//添加评论
    boolean insertFavourites(int goodid, int number,int userid) throws Exception;//添加购物车
    boolean changeBuy(double[] buy,int goodid,String name,int num) throws Exception;//修改数据库数据（数目和金额）
    int check(String name,String password) throws Exception;
    boolean check(String name) throws Exception;
    double[] checkBuy(int goodid,String name,int num) throws Exception;//判断商品剩余数目和剩余金额并返回
    boolean changeName(String oldName,String newName) throws Exception;
    boolean changeBalance(String username, double money) throws SQLException;//充值余额
    boolean changeBalance2(String username) throws SQLException;//开通Vip扣除余额
    boolean changeVip(String username) throws SQLException;//开通Vip
    boolean changeFavourites(int userid) throws Exception;
    int check(int id,String password) throws Exception;
    double checkBalance(String username) throws SQLException;//获取余额、
    int getid (String user);   //根据一个已经存在的用户名，返回他的id
    String getuser(int id);//根据一个已存在的用户id，返回他的用户名
    String getgoodName(int goodid);//获得指定商品的名字

    ArrayList queryCheckreview();//返回数据库review表查询结果集
    ArrayList queryBuy();//返回数据库buy表结果集

}
