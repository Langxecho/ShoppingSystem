package org.example.dao.impl;

import org.example.dao.AdminDAO;
import org.example.domain.Goods;
import org.example.domain.User;
import org.example.util.JdbcUtil;
import org.example.util.PstmtUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

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

    @Override
    //删除评论
    public boolean deleteReview(int goodid, int userid) throws SQLException {
        String sql = "delete from review where goodid = "+goodid+" and userid = "+userid+"";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.executeUpdate(sql);
        System.out.println("删除成功");
        pstmtUtil.closeConnection();
        return false;
    }

//    @Override
//    //删除商品选中行
//    public JTable deleteForm(int index, JTable table) {
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.removeRow(index);
//        return table;
//    }

    @Override
    //备份数据库
    public boolean backup() throws Exception {
        String url = "jdbc:mysql://106.14.246.27:3306/shoppingsystem?useUnicode=true&characterEncoding=utf-8";
        String userName = "root";
        String password = "kobe24";
        String databaseName = "shoppingsystem";
        String backupPath = "D:/db_backup.sql";
        Connection con = DriverManager.getConnection(url, userName, password);
        String dumpCommand = "C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump.exe -u " + userName + " -p" + password + " " + databaseName + " --result-file=" + backupPath;
        Process process = Runtime.getRuntime().exec(dumpCommand);
        con.close();
        process.waitFor();



        
        System.out.println("数据库备份成功");
        return false;
    }

    @Override
    public boolean restore() throws Exception {
        String url = "jdbc:mysql://106.14.246.27:3306/shoppingsystem?useUnicode=true&characterEncoding=utf-8";
        String userName = "root";
        String password = "kobe24";
        String backupPath = "D:/db_backup.sql";
        try (Connection conn = DriverManager.getConnection(url, userName, password);
             Statement stmt = conn.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(backupPath))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String[] sqlStatements = sb.toString().split(";"); // 将 SQL 语句拆分

            for (String sql : sqlStatements) {
                stmt.executeUpdate(sql); // 执行 SQL 语句
            }

            System.out.println("Database restore completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("数据库还原成功");
        return false;
    }
}
