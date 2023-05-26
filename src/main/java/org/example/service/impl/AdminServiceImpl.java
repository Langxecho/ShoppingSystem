package org.example.service.impl;

import org.example.dao.impl.AdminDaoImpl;
import org.example.dao.impl.UserDaoImpl;
import org.example.domain.Goods;
import org.example.domain.buy;
import org.example.service.AdminService;
import org.example.util.PstmtUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
            if (goods.getName().equals(name)){
                target = goods;
                break;
            }
        }
        String[][] rowData = new String[1][7];
            rowData[0][0] = String.valueOf(target.getName());
            rowData[0][1] = String.valueOf(target.getCategory());
            rowData[0][2] = String.valueOf(target.getPrice());
            rowData[0][3] = String.valueOf(target.getDiscount());
            rowData[0][4] = String.valueOf(target.getPortprice());
            rowData[0][5] = String.valueOf(target.getStore());
            rowData[0][6] = String.valueOf(target.getId());

        DefaultTableModel de = new DefaultTableModel(rowData, columnName);
        table.setModel(de);
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
        String[][] rowData = new String[1][7];

            rowData[0][0] = String.valueOf(target.getName());
            rowData[0][1] = String.valueOf(target.getCategory());
            rowData[0][2] = String.valueOf(target.getPrice());
            rowData[0][3] = String.valueOf(target.getDiscount());
            rowData[0][4] = String.valueOf(target.getPortprice());
            rowData[0][5] = String.valueOf(target.getStore());
            rowData[0][6] = String.valueOf(target.getId());

        DefaultTableModel de = new DefaultTableModel(rowData, columnName);
        table.setModel(de);
        return table;
    }

    @Override
    //删除评论
    public boolean delReview(int goodid, int userid) throws SQLException {
        AdminDaoImpl adminDao = new AdminDaoImpl();
        adminDao.deleteReview(goodid, userid);
        return false;
    }

    @Override
    public boolean backup(String username,String pwd,String url,String path,String tableName) throws Exception {
        //mysqldump -h 127.0.0.1 -uroot -proot mysql user >D:/info/server/var/backupdata/backups.sql
        String dbName = "mysql";
        dbName += " "+tableName;
        String pathSql = path+tableName+".sql";
        File fileSql = new File(pathSql);
        File filePath = new File(path);
        //创建备份sql文件
        if (!filePath.exists()){
            filePath.mkdirs();
        }
        if (!fileSql.exists()){
            fileSql.createNewFile();
        }
        //mysqldump -hlocalhost -uroot -p123456 db > /home/back.sql
        StringBuffer sb = new StringBuffer();
        sb.append("mysqldump");
        sb.append(" -h"+url);
        sb.append(" -u"+username);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" >" + " --default-character-set=utf8");
        sb.append(pathSql);
        System.out.println("cmd命令为："+sb.toString());
        System.out.println("开始备份："+dbName);
        Process process = null;
        //判断操作系统 windwos与linux使用的语句不一样
        if(System.getProperty("os.name").toLowerCase().indexOf("windows") > -1){
            process = Runtime.getRuntime().exec("cmd /c"+sb.toString());
        }else if(System.getProperty("os.name").toLowerCase().indexOf("linux") > -1){
            process = Runtime.getRuntime().exec("/bin/sh -c"+sb.toString());
        }else{
            throw new Exception("暂不支持该操作系统，进行数据库备份或还原！");
        }
        //设置超时一分钟
        process.waitFor(60000, TimeUnit.MILLISECONDS);
        //输出返回的错误信息
        StringBuffer mes = new StringBuffer();
        String tmp = "";
        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while((tmp = error.readLine()) != null){
            mes.append(tmp + "\n");
        }
        if(mes != null || !"".equals(mes) ){
            System.out.println("备份成功!==>"+mes.toString());
        }
        error.close();
        return true;
    }

    @Override
    public boolean restore() {
return true;
    }

    @Override
    public boolean print(int all,int soldnumber,double profits) {

                ArrayList<buy> myList = new UserDaoImpl().queryBuy();  // 假设 myList 是存储对象的 ArrayList

                // 假设 myList 已经包含了一些对象

                StringBuilder sb = new StringBuilder();

                // 遍历 ArrayList，将每个对象的 toString 结果追加到 StringBuilder
                for (buy obj : myList) {
                    sb.append(obj.toString()).append("\n");
                }
                sb.append("利润:" + profits + "元" + "销量:" + soldnumber + "商品总数量:" + all);
                // 指定要输出的文件路径
                String filePath = "E:\\output.txt";

                // 将 StringBuilder 对象中的内容写入文件
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            return true;

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

    @Override
    public int goodNumber() {
        ArrayList<Goods> arrayList = new AdminDaoImpl().checkGoods();
        int Allnumber = 0;
        for (int i = 0;i < arrayList.size();i++){
            Goods goo = arrayList.get(i);
            Allnumber += goo.getStore();
        }
        return Allnumber;
    }

    @Override
    public int soldNumber() {
        ArrayList<buy> arrayList = new UserDaoImpl().queryBuy();
        int ALLcount = 0;
        for (int i = 0;i < arrayList.size();i++){
            buy by = arrayList.get(i);
            ALLcount += by.getCount();
        }

        return ALLcount;
    }

    @Override
    public Double profits() {
        ArrayList<buy> buyList = new UserDaoImpl().queryBuy();
        ArrayList<Goods> goodsList = new AdminDaoImpl().checkGoods();
        Double ALLprofits = 0.0;
        for (int i = 0;i < buyList.size();i++){
            buy by = buyList.get(i);
        int CUcount = by.getCount();//当前buy表记录该购买记录的购买数量
        int CUgoodid = by.getGoodid();//当前当前buy表记录该购买记录的购买商品id
            for (int j = 0;j < goodsList.size();j++){
                Goods goo = goodsList.get(j);

                if (CUgoodid == goo.getId()){
                    ALLprofits += CUcount * ( goo.getPrice() - goo.getPortprice() );
                }
            }
        }
        return ALLprofits;
    }
}
