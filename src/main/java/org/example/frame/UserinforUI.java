package org.example.frame;


import org.example.domain.User;
import org.example.service.impl.UserServiceImpl;
import org.example.util.PstmtUtil;
import org.example.util.getMiddlelocation;
import org.example.util.showError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserinforUI extends JPanel {
    String getUser;
    String user = null;//用户名
    JLabel balancefont;
    String id = null;//用户id
    String vip = "否";//是否是VIP
    double balance = 0.0;//用户余额
    JButton changename = new JButton("修改");//修改用户名按钮
    JButton getVIP = new JButton("充值");//VIP充值按钮
    JButton addbalance = new JButton("充值");//余额充值按钮
    JLabel userfont;
    JLabel vipfont;
    //占位符
    String empty = "                                                                                                                                                                                                                                                                                                                                                         ";
    JLabel emptys = new JLabel(empty);
    public UserinforUI(String getUser) {
        this.getUser = getUser;
        //数据库加载设置
        try{
            System.out.println("元数据得到的用户名为"+getUser);
            String sql = "select * from user where username = " + "'"+ getUser + "'";
            PstmtUtil pre = new PstmtUtil();
            PreparedStatement re = pre.PstmtUtil(sql);
            ResultSet rs = re.executeQuery();
            rs.next();
            user = rs.getString("username");
            id = rs.getString("id");
            balance = rs.getDouble("balance");
            int vipflag = rs.getInt("identity");
            vip = (vipflag == 2 ? "是" : "否");
            System.out.println("元数据加载成功");
//            db.closeConnection(con);
            pre.closeConnection();
            re.close();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("元数据加载失败");
        }
        //--------------------------------
        setLayout(null);
        //信息承载面板设置
        JPanel undertake = new JPanel();
        undertake.setBounds(20,24,740,500);
        undertake.setBackground(Color.WHITE);
        undertake.setLayout(null);
        add(undertake);
        //字体设置
        Font font = new Font("微软雅黑", Font.PLAIN, 22);
        //用户名区域
        userfont = new JLabel("用户名:" + user);
        userfont.setFont(font);
        userfont.setBounds(40,80,200,40);
        undertake.add(userfont);
        //用户名修改按钮设置
        changename.setBounds(200,90,60,25);
        undertake.add(changename);
        changename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //这里添加用户名修改按钮的功能
                exchangeui();
            }
        });
        //用户id区域设置
        JLabel useridfont = new JLabel("用户id:" + id);
        useridfont.setFont(font);
        useridfont.setBounds(40,120,200,40);
        undertake.add(useridfont);
        //VIP区域设置
        vipfont = new JLabel("vip:" + vip);
        vipfont.setFont(font);
        vipfont.setBounds(40,160,200,40);
        undertake.add(vipfont);
        getVIP.setBounds(200,170,60,25);
        undertake.add(getVIP);
        getVIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //这里添加VIP充值按钮的功能
                getVIPui();
            }
        });
        //余额界面设置
        balancefont = new JLabel("账户余额:" + balance + "元");
        balancefont.setFont(font);
        balancefont.setBounds(40,200,200,40);
        undertake.add(balancefont);
        addbalance.setBounds(230,210,60,25);
        undertake.add(addbalance);
        addbalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //这里添加余额充值按钮的功能
                addbalance();
            }
        });
    }
    JFrame getVIPui(){
        JFrame getvip = new JFrame("修改用户名");
        getvip.setSize(510,120);
        int a[] = getMiddlelocation.getMiddlelocate(getvip);
        getvip.setLocation(a[0],a[1]);
        getvip.setLayout(null);
        JLabel lai = new JLabel("将从您的账户中扣除300元来将您的账户升级为vip账户,您将享受所有商品8折优惠(可累乘)");
        lai.setBounds(3,2,500,60);
        getvip.add(lai);
        JButton queren = new JButton("确认购买");
        JButton qvxiao = new JButton("取消");
        queren.setBounds(60,49,100,25);
        queren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //这里添加vip获取的相关设置
                Boolean flag = false;
                try {
                    flag = new UserServiceImpl().getVip(getUser);
                } catch (SQLException ex) {
                    System.out.println("找不到用户");
                    ex.printStackTrace();
                }

                if (flag){
                    showError showError = new showError();
                        showError.showError("恭喜","您已经成为高贵的vip用户了");
                        getvip.dispose();
                    //再次判断
                    vip = "是";
                    vipfont.setText("VIP:" + vip);
                    SwingUtilities.updateComponentTreeUI(vipfont);
                    getvip.dispose();
                    }
                    else if(!flag){
                        showError showError = new showError();
                        showError.showError("出错","您的余额不足,请充值!");
                        getvip.dispose();
                    }
            }
        });
        getvip.add(queren);
        qvxiao.setBounds(340,49,100,25);
        qvxiao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getvip.dispose();
            }
        });
        getvip.add(qvxiao);
        getvip.setVisible(true);
        return getvip;
    }
    JFrame exchangeui(){
        JFrame exchange = new JFrame("修改用户名");
        exchange.setSize(250,120);
        int a[] = getMiddlelocation.getMiddlelocate(exchange);
        exchange.setLocation(a[0],a[1]);
        exchange.setLayout(null);
        TextField input = new TextField();
        input.setBounds(60,4,120,30);
        JButton cc = new JButton("修改");
        cc.setBounds(80,38,80,25);
        cc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newname = input.getText();
                try {
                    new UserServiceImpl().changeName(getUser,newname);
                } catch (Exception ex) {
                    System.out.println("修改用户名失败");
                    ex.printStackTrace();
                }
                System.out.println("修改成功");
                    showError showError = new showError();
                    showError.showError("祝贺","用户名修改成功!");
                    exchange.dispose();
                    user = newname;
                    //更新组件信息
                    userfont.setText("用户名:" + user);
                    SwingUtilities.updateComponentTreeUI(userfont);
        }});
        exchange.add(cc);
        exchange.add(input);
        exchange.setVisible(true);
        return exchange;
    }
    JFrame addbalance(){
        JFrame addbalance = new JFrame("请输入充值金额");
        addbalance.setSize(280,120);
        int a[] = getMiddlelocation.getMiddlelocate(addbalance);
        addbalance.setLocation(a[0],a[1]);
        addbalance.setLayout(null);
        TextField input = new TextField();
        input.setBounds(70,4,120,30);
        JButton cc = new JButton("充值");
        cc.setBounds(90,38,80,25);
        cc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //用户输入的存款金额
                Double add =Double.parseDouble( input.getText());
        //这里添加充值的操作按钮
                try {
                    new UserServiceImpl().topUp(getUser,add);
                } catch (SQLException ex) {
                    System.out.println("余额充值失败");
                    ex.printStackTrace();
                }

                showError showError = new showError();
                showError.showError("恭喜","充值成功");
                    addbalance.dispose();
                    balance += add;
                    balancefont.setText("账户余额:" + balance + "元");
                SwingUtilities.updateComponentTreeUI(balancefont);
            }
        });
        addbalance.add(input);
        addbalance.add(cc);
        addbalance.setVisible(true);
        return addbalance;
    }
}
