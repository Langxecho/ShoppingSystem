package org.example.frame;

import org.example.dao.impl.AdminDaoImpl;
import org.example.dao.impl.UserDaoImpl;
import org.example.domain.Goods;
import org.example.service.impl.UserServiceImpl;
import org.example.util.getMiddlelocation;
import org.example.util.showError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class BuyUI extends JFrame{
    public String user;
    public JTable table;
    private Timer timer;
    private Object value = null;

    private String goodname;
    private Integer goodstore;
    private Double gooddiscount;
    private double goodprice;
    private int goodid;


    public static JFrame buy = new JFrame();
    public static JFrame b = new JFrame();
    public BuyUI(String user, JTable table) {
        this.user = user;
        this.table = table;
    }
    public void showBuy() {
        //获取商品信息
        AdminDaoImpl adminDao = new AdminDaoImpl();
        ArrayList<Goods> arrayList = adminDao.checkGoods();
        int index = table.getSelectedRow();
        Goods goods = arrayList.get(index);
        goodname = goods.getName();
        goodstore = goods.getStore();
        gooddiscount = goods.getDiscount();
        goodprice = goods.getPrice();
        goodid = goods.getId();


//        商品详情界面

        buy.setSize(500, 350);
        buy.setTitle("商品详情");
        buy.setResizable(false);

//        面板设置
        JPanel root = new JPanel();
        buy.setContentPane(root);
        root.setLayout(null);

//        字体设置
        Font font = new Font("微软雅黑", Font.PLAIN, 20);

//        标签组件
        JLabel name = new JLabel("商品名:" + goodname);
        name.setBounds(20, 20, 200, 30);
        name.setFont(font);
        root.add(name);

        JLabel count = new JLabel("库存:" + goodstore);
        count.setBounds(20, 100, 200, 30);
        count.setFont(font);
        root.add(count);

        JLabel price = new JLabel("折扣价:" + gooddiscount);
        price.setBounds(20, 180, 200, 30);
        price.setFont(font);
        root.add(price);

        JPanel content = new JPanel();
        root.add(content);
        content.setBounds(230, 24, 230, 243);
        content.setBackground(Color.white);
        content.setLayout(null);

        JLabel title = new JLabel("购买详情");
        title.setBounds(73, 20, 80, 30);
        title.setFont(font);
        content.add(title);

        JLabel num = new JLabel("数量:");
        num.setBounds(30, 120, 80, 30);
        num.setFont(font);
        content.add(num);


        JLabel total = new JLabel("价格总计:");
        total.setBounds(30, 160, 200, 30);
        total.setFont(font);
        content.add(total);

        //        输入数量部分
        JSpinner spinner = new JSpinner();
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 999, 1);
        spinner.setModel(model);
        spinner.setBounds(90, 125, 60, 25);
        content.add(spinner);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                value = model.getValue(); // 获取当前模型中的值，类型为Object
                double number = ((Number)value).doubleValue(); // 将值转换为double类型
                number = number * goodprice;
                String labelText = String.format("%.2f", number); // 将结果格式化为两位小数
                total.setText("价格总计:" + labelText + "元");
            }
        });
        timer.start();

//        按钮部分
        JButton exit = new JButton("返回");
        exit.setBounds(20, 274, 60, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                buy.dispose();
            }
        });
        root.add(exit);

        JButton review = new JButton("查看评论");
        review.setBounds(120, 274, 90, 30);
        review.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReviewUI reviewUI = new ReviewUI(user);
                JFrame a = reviewUI.generatereview();
            }
        });
        root.add(review);

        JButton b1 = new JButton("购买");
        b1.addActionListener((e) -> {
            try {
                UserServiceImpl userService = new UserServiceImpl();
                if ((int)value == 0) {
                    back2("购买失败", "请输入购买数量");
                } else if(!userService.buy(goodid, user, (int)value)) {
                    back2("购买失败", "商品库存不足");
                }else {
                buy();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        b1.setBounds(25, 204, 60, 30);
        content.add(b1);

        JButton b2 = new JButton("添加购物车");
        b2.addActionListener((e) -> {
            UserServiceImpl userService = new UserServiceImpl();
            UserDaoImpl userDao = new UserDaoImpl();
            try {
                if ((int)value == 0) {
                    back2("添加失败", "请输入添加数量");
                } else {
                boolean bl = userService.addFavourites(goodid, (int)value, userDao.getid(user));
                if (bl == true) {
                    back2("添加成功", "添加成功!");
                } else {
                    back2("添加失败", "商品库存不足");
                }}
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        b2.setBounds(110, 204, 100, 30);
        content.add(b2);


//        窗口位置居中
        int[] location = getMiddlelocation.getMiddlelocate(buy);
        buy.setLocation(location[0], location[1]);

        buy.setVisible(true);
    }


    public void buy() throws SQLException {
//        购买窗口界面

        b.setSize(400, 250);
        b.setTitle("商品购买");
        b.setResizable(false);

//        面板界面
        JPanel root = new JPanel();
        b.setContentPane(root);
        root.setLayout(null);

//        字体设置
        Font font = new Font("微软雅黑", Font.PLAIN, 18);

        JLabel name = new JLabel("商品名:" + goodname);
        name.setBounds(20, 13, 200, 30);
        name.setFont(font);
        root.add(name);

        JLabel count = new JLabel("购买数量:" + (int)value);
        count.setBounds(20, 50, 200, 30);
        count.setFont(font);
        root.add(count);


        JLabel money = new JLabel();
        String change = String.format("%.2f", (int)value * goodprice); // 将结果格式化为两位小数
        money.setText("需支付:" + change + "元");
        money.setBounds(20, 87, 200, 30);
        money.setFont(font);
        root.add(money);

        UserDaoImpl userDao = new UserDaoImpl();
        double balance1 = userDao.checkBalance(user);
        JLabel account = new JLabel("账号余额:" + balance1);
        account.setBounds(20, 124, 200, 30);
        account.setFont(font);
        root.add(account);

        JLabel account2 = new JLabel("购后余额:" + (balance1 - (int)value * goodprice));
        account2.setBounds(20, 161, 200, 30);
        account2.setFont(font);
        root.add(account2);

        JPanel content = new JPanel();
        root.add(content);
        content.setBounds(205, 30, 150, 150);
        content.setBackground(Color.white);
        content.setLayout(null);

        JLabel sure = new JLabel("您确定购买吗？");
        sure.setBounds(15, 10, 140, 60);
        sure.setFont(font);
        content.add(sure);

        JButton yes = new JButton("确定");
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserServiceImpl userService = new UserServiceImpl();
                userService.buy(goodid, user, (int)value);
                back();
            }
        });
        yes.setBounds(10, 80, 60, 30);
        content.add(yes);

        JButton no = new JButton("取消");
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.dispose();
            }
        });
        no.setBounds(80, 80, 60, 30);
        content.add(no);


//        窗口位置居中
        int[] location = getMiddlelocation.getMiddlelocate(b);
        b.setLocation(location[0], location[1]);

        b.setVisible(true);
    }

    public void back() {
        //        界面设置
        JFrame back = new JFrame();
        back.setSize(250, 150);
        back.setTitle("购买成功");
        back.setResizable(false);

//        容器设置
        JPanel root = new JPanel();
        back.setContentPane(root);
        back.setLayout(null);

//        输入错误提示
        JLabel label = new JLabel("谢谢惠顾!");
        label.setBounds(-8, 9, 250, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        root.add(label);

//        确定按钮
        JButton button = new JButton("确定");
        button.setBounds(87, 65, 60, 27);
        button.addActionListener((e) -> {

            back.dispose();
            b.dispose();
            buy.dispose();

        });
        root.add(button);

//        窗口位置居中
        int[] location =  getMiddlelocation.getMiddlelocate(back);
        back.setLocation(location[0], location[1]);

        back.setVisible(true);
    }

    public void back2(String title, String content) {
        //        界面设置
        JFrame back = new JFrame();
        back.setSize(250, 150);
        back.setTitle(title);
        back.setResizable(false);

//        容器设置
        JPanel root = new JPanel();
        back.setContentPane(root);
        back.setLayout(null);

//        输入错误提示
        JLabel label = new JLabel(content);
        label.setBounds(-8, 9, 250, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        root.add(label);

//        确定按钮
        JButton button = new JButton("确定");
        button.setBounds(87, 65, 60, 27);
        button.addActionListener((e) -> {
            back.dispose();
            buy.dispose();
        });
        root.add(button);

//        窗口位置居中
        int[] location =  getMiddlelocation.getMiddlelocate(back);
        back.setLocation(location[0], location[1]);

        back.setVisible(true);
    }
}
