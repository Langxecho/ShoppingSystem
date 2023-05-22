package org.example.dao;

import org.example.domain.User;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminDAO {
    boolean checkLogin(User user);
    ArrayList checkGoods();//将所有的商品信息调出，储存在数组当中
    boolean deleteReview(int goodid, int userid) throws SQLException;//删除评论
//    JTable deleteForm(int index, JTable table);//删除商品选中行

    boolean backup() throws Exception;//备份数据库
    boolean restore() throws Exception;//还原数据库


}
