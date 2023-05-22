package org.example.frame;

import org.example.dao.impl.UserDaoImpl;
import org.example.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyinforUI extends JPanel {
    String user;
    JTable table;
    String empty1 = "                                                                                                                                                                                                                                                                                                                                                                             ";

    public BuyinforUI(String user) {
        this.user = user;
        setLayout(null);

//        表格

        table = new UserServiceImpl().initbuyTable(new UserDaoImpl().getid(user));
//        System.out.println("shhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        setLayout(new FlowLayout(FlowLayout.LEFT));

//        滚动
        JLabel emptys = new JLabel(empty1);
        add(emptys);
        add(emptys);

        //添加两行占位符
        JScrollPane scrollPane = new JScrollPane(
                table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setPreferredSize(new Dimension(778,400));
        add(scrollPane);
        //添加累计信息
        add(calculate());
        //查看评论按钮设置
        JButton review = new JButton("查看评论");
        review.setPreferredSize(new Dimension(100,25));
        add(review);
        review.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //关联评论界面
                ReviewUI a = new ReviewUI(user);
                a.generatereview();

            }
        });

    }
    /*
     * @Author Langxecho
            * @Description //TODO 此方法用于统计并且用label显示累计购买的商品数量
     * @Date 8:23 2023/3/23
     * @Param
            * @return
            **/
    JLabel calculate(){
        int a = 0;//统计累积购买多少件商品
        double money = 0;//统计累积花费多少钱
        JLabel lable = new JLabel("累计购买:" + a + "件" + money + "元" + "                                                                                                                                                                                                                                           ");
//        lable.setSize(770,40);
        return lable;
    }
}
