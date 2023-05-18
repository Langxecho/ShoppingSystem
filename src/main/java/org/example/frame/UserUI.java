package org.example.frame;

import org.example.util.getMiddlelocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserUI extends JFrame{
    String getuser = null;
    public void showUser() {
        System.out.println("用户主界面得到的用户名为" + getuser);
//        用户界面
        JFrame user = new JFrame();
        user.setSize(800, 600);
        user.setTitle("用户界面");
        user.setDefaultCloseOperation(EXIT_ON_CLOSE);
        user.setResizable(false);

//        使用卡片布局
        CardLayout cardLayout = new CardLayout();

//        界面内面板
        JPanel root = new JPanel();
        user.setContentPane(root);
        root.setLayout(null);

//        切换面板
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 500);
        root.add(panel);
        panel.setLayout(cardLayout);
//        添加卡片
        GoodsetUI goodsetUI = new GoodsetUI();
        panel.add(goodsetUI, "goodsetUI");
//
        UserinforUI userinforUI = new UserinforUI(getuser);
        panel.add(userinforUI, "userinforUI");

//        FavouritesUI favouritesUI = new FavouritesUI(getuser);
//        panel.add(favouritesUI, "favouritesUI");
//
        BuyinforUI buyinforUI = new BuyinforUI();
        panel.add(buyinforUI, "buyinforUI");

//        按钮创建
        JButton button1 = new JButton("商品购买");
        JButton button2 = new JButton("用户信息");
        JButton button3 = new JButton("购物车");
        JButton button4 = new JButton("购买信息");
        JButton button5 = new JButton("退出");

//        切换商品购买按钮
        button1.setEnabled(false);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "goodsetUI");
                button1.setEnabled(false);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
            }
        });
        button1.setBounds(30, 515, 100, 35);
        root.add(button1);

//        切换用户信息按钮

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "userinforUI");
                button2.setEnabled(false);
                button1.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
            }
        });
        button2.setBounds(140, 515, 100, 35);
        root.add(button2);

//        切换购物车按钮
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "favouritesUI");
                button3.setEnabled(false);
                button1.setEnabled(true);
                button2.setEnabled(true);
                button4.setEnabled(true);
            }
        });
        button3.setBounds(250, 515, 100, 35);
        root.add(button3);

//        切换购买信息按钮
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "buyinforUI");
                button4.setEnabled(false);
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
            }
        });
        button4.setBounds(360, 515, 100, 35);
        root.add(button4);

//        退出按钮
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        button5.setBounds(470, 515, 100, 35);
        root.add(button5);

//        窗口位置居中
        int[] location = getMiddlelocation.getMiddlelocate(user);
        user.setLocation(location[0], location[1]);

        user.setVisible(true);

    }

    public void setGetuser(String getuser) {
        this.getuser = getuser;
    }
}
