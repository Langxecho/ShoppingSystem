package org.example.frame;

import org.example.dao.impl.UserDaoImpl;
import org.example.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuyinforUI extends JPanel {
    String user;
    JTable table;
    String empty1 = "                                                                                                                                                                                                                                                                                                                                                                             ";
    int index;
    public BuyinforUI(String user) {
        this.user = user;
        setLayout(null);

//        表格

        table = new UserServiceImpl().initbuyTable(new UserDaoImpl().getid(user));
//        System.out.println("shhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        setLayout(new FlowLayout(FlowLayout.LEFT));
// 设置表格的获取行数方法
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                index = table.rowAtPoint(e.getPoint());
            }
        });
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
        //查看评论按钮设置
        JButton review = new JButton("查看评论");
        review.setPreferredSize(new Dimension(100,25));
        add(review);
        review.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取选中的行数

                String goodname = (String) table.getValueAt(index, 0);
//                System.out.println(goodname);
                int goodids = new UserDaoImpl().getgoodid(goodname);
                //关联评论界面
//                System.out.println(goodname);
//                System.out.println(user);
//                System.out.println(goodids);
                ReviewUI a = new ReviewUI(user);
                a.goodid = goodids;
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

}
