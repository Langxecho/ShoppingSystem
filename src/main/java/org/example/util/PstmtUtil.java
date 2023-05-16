package org.example.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PstmtUtil {
    //为方法提供一个sql语句，之后方法返回一个preparestatement的会话
    //创建的连接接的关掉
    JdbcUtil db = new JdbcUtil();
    Connection con;
    PreparedStatement pre;
    public PreparedStatement PstmtUtil(String sql){

        try {
            con = db.getConnection();
            pre = con.prepareStatement(sql);
        } catch (Exception e) {
            System.out.println("数据库连接失败");
            throw new RuntimeException(e);
        }

        return pre;
    }
    public void closeConnection(){

        try {
            pre.close();
            db.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("数据库关闭失败");
            throw new RuntimeException(e);
        }

    }
}
