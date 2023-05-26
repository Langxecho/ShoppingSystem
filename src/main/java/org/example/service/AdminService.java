package org.example.service;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public interface AdminService {
    JTable table();//生成管理员界面初始化的表格
    //添加商品，这几个形参分别是商品名，单价，进价，商品id，类别，折扣，库存
    boolean addGood(String name,Double price,Double portPrice,int goodid,String category,Double discount,int store);
    JTable findGood(String name,JTable table);//商品名称查找商品
    JTable findGood(int id, JTable table);//商品id查找商品
    boolean delReview(int goodid,int userid) throws SQLException;//删除评论
    boolean backup(String username,String pwd,String url,String path,String tableName) throws Exception;//备份数据库
    boolean restore();//恢复数据库
    boolean print(int all,int soldnumber,double profits);//打印表单
    JTable delForm(int index,JTable table);//管理员界面删除商品方法，输入要删除的行，返回一个修改后的表格
    JTable flashForm(JTable table);//管理员界面刷新表格方法，返回刷新后的表格
    int goodNumber();//计算商品总数量
    int soldNumber();//计算销量
    Double profits();//计算利润
}
