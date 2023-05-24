package org.example.frame;



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
JLabel l1 = new JLabel("商品总数量:" + all);
JLabel l2 = new JLabel("销量:" + soldnumber);
JLabel l3 = new JLabel("利润:" + profits + "元");
JButton back = new JButton("返回");
JButton printbutton = new JButton("打印报表");
JButton datasave = new JButton("数据备份");

    public void startUI(){
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
        l2.setBounds(45,55,300,60);
        l2.setFont(new Font("SimSong",Font.BOLD,20));
        l3.setBounds(45,95,300,60);
        l3.setFont(new Font("SimSong",Font.BOLD,20));
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
        datasave.setBounds(245,280,100,30);
        frame.add(back);
        frame.add(printbutton);
        frame.add(datasave);
        frame.setVisible(true);
    }

}
