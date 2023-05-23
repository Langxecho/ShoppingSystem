package org.example.dao.impl;

import org.example.dao.AdminDAO;
import org.example.domain.Goods;
import org.example.domain.User;
import org.example.util.PstmtUtil;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/17 8:35
 */

public class AdminDaoImpl implements AdminDAO {
    @Override
    public boolean checkLogin(User user) {
        return false;
    }

    @Override
    public ArrayList checkGoods() {
        ArrayList<Goods> arrayList = new ArrayList(10);
        String sql = "Select * from goods";
        PstmtUtil pst = new PstmtUtil();
        PreparedStatement pre =  pst.PstmtUtil(sql);
        ResultSet rs;
        try {
            rs = pre.executeQuery();
            while(rs.next()){
                Goods go = new Goods();
                go.setId(rs.getInt("id"));
                go.setStore(rs.getInt("store"));
                go.setPortprice(rs.getDouble("portprice"));
                go.setDiscount(rs.getDouble("discount"));
                go.setPrice(rs.getDouble("price"));
                go.setCategory(rs.getString("category"));
                go.setName(rs.getString("name"));
                arrayList.add(go);
            }
        } catch (SQLException e) {
            System.out.println("数据库查询失败");
            throw new RuntimeException(e);
        }
        pst.closeConnection();
        try {
            pre.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }
}
