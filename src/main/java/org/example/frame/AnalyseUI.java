package org.example.frame;



import org.example.service.impl.AdminServiceImpl;
import org.example.util.getMiddlelocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AnalyseUI {
    int all;
    int soldnumber;
    double profits;
JLabel l1 = new JLabel();
JLabel l2 = new JLabel();
JLabel l3 = new JLabel();
JButton back = new JButton("返回");
JButton printbutton = new JButton("打印报表");
JButton datasave = new JButton("数据备份");

    public void startUI(){
        all = new AdminServiceImpl().goodNumber();
        soldnumber = new AdminServiceImpl().soldNumber();
        profits = new AdminServiceImpl().profits();
        JFrame frame = new JFrame("数据分析");
        //这里添加初始化数据的方法
        //frame的基本操作
        frame.setSize(600,400);
        int a[] = getMiddlelocation.getMiddlelocate(frame);
        frame.setLocation(a[0],a[1]);
        //设置所有的组件信息
        //label设置
        frame.setLayout(null);
        l1.setBounds(45,15,300,60);
        l1.setFont(new Font("SimSong",Font.BOLD,20));
        l1.setText("商品总数量:" + all);
        l2.setBounds(45,55,300,60);
        l2.setFont(new Font("SimSong",Font.BOLD,20));
        l2.setText("销量:" + soldnumber);
        l3.setBounds(45,95,300,60);
        l3.setFont(new Font("SimSong",Font.BOLD,20));
        l3.setText("利润:" + profits + "元");
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        //按钮设置
        back.setBounds(45,280,60,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        printbutton.setBounds(125,280,100,30);
        printbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminServiceImpl().print(all,soldnumber,profits);
            }
        });
        datasave.setBounds(245,280,100,30);
        frame.add(back);
        frame.add(printbutton);
//        frame.add(datasave);
        frame.setVisible(true);
    }

}
