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
    public int check(int id, String password) throws Exception {
        String sql = "select * from user where id = ? and passworld = ?";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pstmtUtil.closeConnection();
        return 0;
    }


}
