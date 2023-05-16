package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/16 8:46
 */

public class JdbcUtil {
    //mysql驱动名
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    //数据库连接地址
    private static String url = "jdbc:mysql://106.14.246.27:3306/shoppingsystem?useUnicode=true&characterEncoding=utf-8";
    //用户名
    private static String userName = "root";
    //密码
    private static String password = "kobe24";
    /*
     * @Author Langxecho
     * @Description //TODO 获取数据库连接
     * @Date 13:37 2023/3/25
     * @Param
     * @return
     **/
    public Connection getConnection() throws Exception {
        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(url,userName,password);
        return connection;
    }
    /*
     * @Author Langxecho
     * @Description //TODO 关闭连接
     * @Date 13:41 2023/3/25
     * @Param
     * @return
     **/
    public void closeConnection(Connection con) throws SQLException {
        if(con != null){
            con.close();
            System.out.println("数据库连接关闭");
        }
    }
}
