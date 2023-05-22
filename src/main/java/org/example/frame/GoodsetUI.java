package org.example.frame;


import org.example.dao.impl.AdminDaoImpl;
import org.example.domain.Goods;
import org.example.service.impl.UserServiceImpl;
import org.example.util.rightMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GoodsetUI extends JPanel {
    String user;
    JTable table;
    public GoodsetUI(String user) {
        setLayout(null);
        this.user = user;
        table = new UserServiceImpl().inittable();

//        滚动
        JScrollPane scrollPane = new JScrollPane(
                table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setBounds(30, 30, 728, 470);
        add(scrollPane);



//        右击菜单
        JPopupMenu menu = new JPopupMenu();
        JMenuItem checkItem = new JMenuItem("查看详情");
        JMenuItem addItem = new JMenuItem("添加购物车");
        JMenuItem buyItem = new JMenuItem("购买商品");
        menu.add(checkItem);
        menu.add(addItem);
        menu.add(buyItem);

        checkItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyUI.showBuy();
            }
        });

        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        buyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  rightMenu.rightMenu(table, e, menu);
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
    }
}
