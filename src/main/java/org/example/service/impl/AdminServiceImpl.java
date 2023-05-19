package org.example.service.impl;

import org.example.dao.impl.AdminDaoImpl;
import org.example.domain.Goods;
import org.example.service.AdminService;
import org.example.util.PstmtUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/16 16:04
 */

public class AdminServiceImpl implements AdminService {

    @Override//创建管理员系统主界面表格的方法
    public JTable table() {
        Object []columnName = new Object[]{"商品名","类别","单价","折扣","进价","库存","商品ID"};//表格的字段名
        JTable table = new JTable();
        AdminDaoImpl a = new AdminDaoImpl();
        ArrayList<Goods> array = a.checkGoods();
        String[][] rowData = new String[array.size()][7];
        for (int i = 0; i < array.size();i++){
            Goods goo = array.get(i);
            rowData[i][0] = String.valueOf(goo.getName());
            rowData[i][1] = String.valueOf(goo.getCategory());
            rowData[i][2] = String.valueOf(goo.getPrice());
            rowData[i][3] = String.valueOf(goo.getDiscount());
            rowData[i][4] = String.valueOf(goo.getPortprice());
            rowData[i][5] = String.valueOf(goo.getStore());
            rowData[i][6] = String.valueOf(goo.getId());
        }
        table = new JTable(rowData,columnName) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setReorderingAllowed(false);   //不可整列移动
        table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//              int index = table.rowAtPoint(e.getPoint());这个东西意义不明啊
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return table;
    }

    @Override
    public boolean addGood(String name, Double price, Double portPrice, int goodid, String category, Double discount, int store) {
        return false;
    }

    @Override//根据商品名查找
    public JTable findGood(String name, JTable table) {
        Goods target = null;
        AdminDaoImpl imp = new AdminDaoImpl();
        ArrayList<Goods> arrayList = imp.checkGoods();
        Object []columnName = new Object[]{"商品名","类别","单价","折扣","进价","库存","商品ID"};//表格的字段名
        for (int i = 0; i < arrayList.size(); i++) {
            Goods goods = arrayList.get(i);
            if (goods.getId().equals(name)){
                target = goods;
                break;
            }
        }
        String[][] rowData = new String[arrayList.size()][7];
        for (int i = 0; i < arrayList.size();i++){
            rowData[i][0] = String.valueOf(target.getName());
            rowData[i][1] = String.valueOf(target.getCategory());
            rowData[i][2] = String.valueOf(target.getPrice());
            rowData[i][3] = String.valueOf(target.getDiscount());
            rowData[i][4] = String.valueOf(target.getPortprice());
            rowData[i][5] = String.valueOf(target.getStore());
            rowData[i][6] = String.valueOf(target.getId());
        }
        table = new JTable(rowData, columnName);
        return table;
    }

    @Override
    //根据商品id查找
    public JTable findGood(int id, JTable table) {
        Goods target = null;
        AdminDaoImpl imp = new AdminDaoImpl();
        ArrayList<Goods> arrayList = imp.checkGoods();
        Object []columnName = new Object[]{"商品名","类别","单价","折扣","进价","库存","商品ID"};//表格的字段名
        for (int i = 0; i < arrayList.size(); i++) {
            Goods goods = arrayList.get(i);
            if (goods.getId() == id){
                target = goods;
                break;
            }
        }
        String[][] rowData = new String[arrayList.size()][7];
        for (int i = 0; i < arrayList.size();i++){
            rowData[i][0] = String.valueOf(target.getName());
            rowData[i][1] = String.valueOf(target.getCategory());
            rowData[i][2] = String.valueOf(target.getPrice());
            rowData[i][3] = String.valueOf(target.getDiscount());
            rowData[i][4] = String.valueOf(target.getPortprice());
            rowData[i][5] = String.valueOf(target.getStore());
            rowData[i][6] = String.valueOf(target.getId());
        }
        table = new JTable(rowData, columnName);
        return table;
    }

    @Override
    public boolean delReview(int goodid, int userid) {
        return false;
    }

    @Override
    public boolean backup() {
        return false;
    }

    @Override
    public boolean restore() {
        return false;
    }

    @Override
    public boolean print() {
        return false;
    }

    @Override
    public JTable delForm(int index,JTable table) {
        AdminDaoImpl imp = new AdminDaoImpl();
        ArrayList<Goods> arrayList = imp.checkGoods();
        Goods go = arrayList.get(index);
        String sql = "delete from goods where id =" + go.getId();
        PstmtUtil ps = new PstmtUtil();
        PreparedStatement pre = ps.PstmtUtil(sql);
        try {
            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Object []columnName = new Object[]{"商品名","类别","单价","折扣","进价","库存","商品ID"};//表格的字段名
        arrayList.remove(index);
        String[][] rowData = new String[arrayList.size()][7];
        for (int i = 0; i < arrayList.size();i++){
            Goods goo = arrayList.get(i);
            rowData[i][0] = String.valueOf(goo.getName());
            rowData[i][1] = String.valueOf(goo.getCategory());
            rowData[i][2] = String.valueOf(goo.getPrice());
            rowData[i][3] = String.valueOf(goo.getDiscount());
            rowData[i][4] = String.valueOf(goo.getPortprice());
            rowData[i][5] = String.valueOf(goo.getStore());
            rowData[i][6] = String.valueOf(goo.getId());
        }
        DefaultTableModel news = new DefaultTableModel(rowData,columnName);
        table.setModel(news);
        return table;
    }

    @Override
    public JTable flashForm(JTable table) {
        AdminDaoImpl imp = new AdminDaoImpl();
        ArrayList<Goods> arrayList = imp.checkGoods();
        Object []columnName = new Object[]{"商品名","类别","单价","折扣","进价","库存","商品ID"};//表格的字段名

        String[][] rowData = new String[arrayList.size()][7];
        for (int i = 0; i < arrayList.size();i++){
            Goods goo = arrayList.get(i);
            rowData[i][0] = String.valueOf(goo.getName());
            rowData[i][1] = String.valueOf(goo.getCategory());
            rowData[i][2] = String.valueOf(goo.getPrice());
            rowData[i][3] = String.valueOf(goo.getDiscount());
            rowData[i][4] = String.valueOf(goo.getPortprice());
            rowData[i][5] = String.valueOf(goo.getStore());
            rowData[i][6] = String.valueOf(goo.getId());
        }
        DefaultTableModel news = new DefaultTableModel(rowData,columnName);
        table.setModel(news);
        return table;
    }
}
