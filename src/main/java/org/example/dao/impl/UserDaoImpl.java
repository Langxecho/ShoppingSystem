package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.domain.User;
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
        int num = pre.executeUpdate(sql);
        System.out.println("操作了" + num + "条数据");
        pstmtUtil.closeConnection();
        System.out.println("你好");
        return false;
    }

    @Override
    public int check(int id, String password) throws Exception {
        return 0;
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
    //用户注册查询用户名
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


}
