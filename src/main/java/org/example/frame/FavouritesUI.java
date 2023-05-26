package org.example.frame;

import org.example.dao.impl.UserDaoImpl;
import org.example.domain.User;
import org.example.service.impl.UserServiceImpl;
import org.example.util.showError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FavouritesUI extends JPanel {

    JScrollPane scrollPane;
    String empty1 = "                                                                                                                                                                                                                                                                                                                                                                             ";
    Double finallypay = 0.0;
    double discountpay = 0.0;
    int goodid[];//商品id
    int vipflag;
    JTable table;
    int buynumber[];//用户选择的购买数量
    String goodname;//商品名
    String store;//商品存量
    int rownumber;//购物车中储存商品的数量
    double vipdiscount;//vip的折扣
    public FavouritesUI(String getuser) {
        setLayout(null);



                table = new UserServiceImpl().initFavouritesTable(new UserDaoImpl().getid(getuser));
                table.getTableHeader().setReorderingAllowed(false);   //不可整列移动
                table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
                System.out.println("表格加载成功");

        setLayout(new FlowLayout(FlowLayout.LEFT));

//        滚动
        JLabel emptys = new JLabel(empty1);
        add(emptys);
        add(emptys);

        //添加两行占位符
        scrollPane = new JScrollPane(
                table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setPreferredSize(new Dimension(778,400));
        add(scrollPane);

        //购买按钮
        JButton buy = new JButton("购买");
        buy.setSize(80,25);
        add(buy);
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ids = new UserDaoImpl().getid(getuser);
                    double spendmoney = new UserDaoImpl().changeFavourites(ids);
//                    table = new UserServiceImpl().initFavouritesTable(new UserDaoImpl().getid(getuser));
//                    table.getTableHeader().setReorderingAllowed(false);   //不可整列移动
//                    table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
//                    table.repaint();
//                    SwingUtilities.updateComponentTreeUI(table);
//                    SwingUtilities.updateComponentTreeUI(scrollPane);
                    table = new UserServiceImpl().flashFavouritesTable(table,ids);
                    table.repaint();
                    showError a = new showError();
                    a.showError("恭喜","购买成功，计算折扣后余额扣除" + spendmoney);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


}
