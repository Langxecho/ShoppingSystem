package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.domain.User;
import org.example.util.JdbcUtil;
import org.example.util.PstmtUtil;

import java.sql.*;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/16 8:46
 */

public class UserDaoImpl implements UserDao {

    @Override
    public int insert(User user) throws SQLException {
        return 0;
    }

    @Override
    //新用户注册时将信息导入数据库
    public boolean insert(int id, String name, String password) throws SQLException {
        String sql = "insert into user(id, balance, identity, username, passworld) values ("+id+", 0, 1, '"+name+"', '"+password+"')";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.executeUpdate(sql);
        pstmtUtil.closeConnection();
        return false;
    }

    @Override
    //充值余额
    public boolean changeBalance(String username, double money) throws SQLException {
        double balance = checkBalance(username) + money;
        String sql = "update user set balance = "+balance+" where username = '"+username+"'";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.executeUpdate(sql);
        System.out.println("充值成功");
        pstmtUtil.closeConnection();
        return false;
    }

    @Override
    //开通Vip扣除余额
    public boolean changeBalance2(String username) throws SQLException {
        double money = checkBalance(username);
        double balance = money - 648;
        String sql = "update user set balance = "+balance+" where username = '"+username+"'";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.executeUpdate(sql);
        pstmtUtil.closeConnection();
        return false;
    }

    @Override
    //开通Vip
    public boolean changeVip(String username) throws SQLException {
        double money = checkBalance(username);
        boolean bl = true;
        if (money >= 648) {
        String sql = "update user set identity = 2 where username = '"+username+"'";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.executeUpdate(sql);
        pstmtUtil.closeConnection();
        changeBalance2(username);
        } else {
            bl = false;
        }
        return bl;
    }

    @Override
    public boolean changeBuy(double[] buy,int goodid,String name) throws Exception{
        boolean flag = true;
        String sql1 = "update goods set store = "+(int)buy[0]+" where id = "+goodid+"";
        String sql2 = "update user set balance = "+buy[1]+" where username = '"+name+"'";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre1 = pstmtUtil.PstmtUtil(sql1);
        PreparedStatement pre2 = pstmtUtil.PstmtUtil(sql2);
        pre1.executeUpdate();
        pre2.executeUpdate();
        pstmtUtil.closeConnection();
        return flag;
    }

    @Override
    public boolean changeName(String oldName,String newName) throws Exception{
        boolean flag = false;
        String sql = "update user set username = ? where username = ?";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.setString(1,newName);
        pre.setString(2,oldName);
        pre.executeUpdate();
        flag = true;
        pstmtUtil.closeConnection();
        return flag;
    }
    @Override
    public int check(String name, String password) throws Exception {
        int num;
        String sql = "select * from user where username = ? and passworld = ?";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.setString(1,name);
        pre.setString(2,password);
        ResultSet resultSet = pre.executeQuery();
        if (resultSet.next()){
            num = resultSet.getInt(3);
            pstmtUtil.closeConnection();
            return num;
        }else {
            pstmtUtil.closeConnection();
            return 9;
        }
    }


    @Override
    //用户注册查询用户名是否存在
    public boolean check(String name) throws Exception {
        String sql = "select username from user where username = ?";
        boolean bl = false;
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.setString(1, name);
        ResultSet resultSet = pre.executeQuery();
        if (resultSet.next()) {
            bl = true;
        }
        pstmtUtil.closeConnection();
        return bl;
    }

    @Override
    public double[] checkBuy(int goodid,String name,int num) throws Exception {
        double[] buy = new double[2];
        String sql1 = "select * from user where username = ?";
        double balance = 0;
        double sum;
        String sql2 = "select * from goods where id = ?";
        double price;
        int store;
        double discount;
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre1 = pstmtUtil.PstmtUtil(sql1);
        PreparedStatement pre2 = pstmtUtil.PstmtUtil(sql2);
        pre1.setString(1,name);
        pre2.setInt(1,goodid);
        ResultSet resultSet1 = pre1.executeQuery();
        ResultSet resultSet2 = pre2.executeQuery();
        if (resultSet1.next() && resultSet2.next()){
            balance = resultSet1.getDouble(2);//获取用户余额
            price = resultSet2.getDouble(3);//获取商品价格
            store = resultSet2.getInt(6);//获取商品数目
            buy[0] = store - num;//存储商品剩余数目
            discount = resultSet2.getFloat(4);//获取商品折扣
            sum = price * discount * num;//计算总价
            buy[1] = balance - sum;//存储余额
            pstmtUtil.closeConnection();
        }
        return buy;
    }




    @Override
    //获取余额
    public double checkBalance(String username) throws SQLException {
        double money = 0;
        String sql = "select * from user where username = ?";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.setString(1, username);
        ResultSet resultSet = pre.executeQuery();
        if (resultSet.next()) {
            money = resultSet.getDouble(2);
        }
        pstmtUtil.closeConnection();
        return money;
    }


}
