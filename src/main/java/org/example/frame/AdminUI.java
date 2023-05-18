package org.example.frame;

import org.example.service.impl.AdminServiceImpl;
import org.example.util.PstmtUtil;
import org.example.util.getMiddlelocation;
import org.example.util.showError;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/*
 * @Author Langxecho
 * @Description //TODO 管理员主界面UI
 * @Date 16:23 2023/3/22
 * @Param
 * @return
 **/
public class AdminUI {
    JScrollPane scro;//滚动面板
    Object []columnName = new Object[]{"商品名","类别","单价","折扣","进价","库存","商品ID"};//表格的字段名
    JFrame admin = new JFrame("管理员界面");//管理员界面主界面
    String user;//当前管理员的用户名
    String empty = "                                                                                                                                                                                                                             ";
    String empty1 = "                                                                                                                                                                                                                                                                                                                                                                             ";
    JLabel emptysmall = new JLabel(empty);//小占位符
    JLabel lable = new JLabel(empty1);//占位符,占用一整行
    JButton addgoods = new JButton("添加商品");//增加商品按钮
    JButton delgoods = new JButton("删除商品");//删除商品按钮
    JButton searchgoods = new JButton("查找商品");//查找商品按钮
    JButton exit = new JButton("退出");//退出系统按钮
    JButton managergood = new JButton("数据分析");//数据分析按钮
    JButton backup = new JButton("备份与恢复");//数据备份与回复按钮
    JTable table;
    int index;
    JFrame generateadmin(){
        admin.setSize(800,600);
        JLabel welcome = new JLabel("欢迎管理员：" + user + empty);
        admin.add(welcome);
        FlowLayout ss = new FlowLayout(FlowLayout.LEFT);
        admin.setLayout(ss);
        admin.add(lable);//添加占位符,这里占用掉两行
        admin.add(lable);//添加占位符,这里占用掉两行
        int a[] = getMiddlelocation.getMiddlelocate(admin);
        admin.setLocation(a[0],a[1]);
        //添加按钮
        initbotton();
        admin.add(addgoods);
        admin.add(delgoods);
        admin.add(searchgoods);
        admin.setResizable(false);
//        admin.add(emptysmall);
        //占位面板中的表格

        //外层滚动面板
        //生成表
        try {
            //这里生成初始化的表格
            AdminServiceImpl pl = new AdminServiceImpl();
            table = pl.table();
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                index = table.rowAtPoint(e.getPoint());
                }
            });
            scro = new JScrollPane(table);//滚动面板
            scro.setPreferredSize(new Dimension(770,440));
            System.out.println("表格初始化成功");
        } catch (Exception e) {
            System.out.println("表格初始化失败");
            e.printStackTrace();
        }
        //----------------
        admin.add(scro);
        admin.add(managergood);
        admin.add(backup);
        admin.add(exit);
        admin.setVisible(true);
        return admin;
    }
    /*
     * @Author Langxecho
     * @Description //TODO 初始化所有按钮(设置外观与监听器)
     * @Date 16:31 2023/3/22
     * @Param
     * @return
     **/

    void initbotton(){
        //添加商品按钮
        addgoods.setSize(60,20);
        addgoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置操作
                try {
                    addGoods();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //删除商品按钮
        delgoods.setSize(60,20);
        delgoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            table = new AdminServiceImpl().delForm(index,table);
            table.repaint();
            }
        });
        //查找商品按钮
        searchgoods.setSize(60,20);
        searchgoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                设置操作

            }
        });
        //退出系统按钮
        exit.setSize(60,20);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                设置操作
                System.exit(0);
            }
        });
        //管理系统按钮
        managergood.setSize(60,20);
        managergood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置操作
            }
        });
        //备份与恢复按钮
        backup.setSize(60,20);
        backup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置操作

            }
        });
    }
    /*
     * @Author Langxecho
     * @Description //TODO 设初始化b表格
     * @Date 17:12 2023/3/22
     * @Param
     * @return
     **/
//    Object[][] rowData;
//    JTable table;
//    public void inittable() throws Exception {
//        //加载数据库
//        String sql = "Select * from goods";
//        dbUtil db = new dbUtil();
//        Connection con = db.getConnection();
//        PreparedStatement pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//        ResultSet rs = pstmt.executeQuery();
//        //获取记录行数
//        rs.last();
//        int numbers = rs.getRow();
//        System.out.println("检索到了" + numbers + "数据");
//        rs.beforeFirst();//指针归位
//        //表格相关设置
//        rowData = new Object[numbers][7];
//        while (rs.next()){
//            rowData[rs.getRow() - 1][0] = rs.getString("name");
//            rowData[rs.getRow() - 1][1] = rs.getString("category");
//            rowData[rs.getRow() - 1][2] = rs.getString("price");
//            rowData[rs.getRow() - 1][3] = rs.getString("discount");
//            rowData[rs.getRow() - 1][4] = rs.getString("portprice");
//            rowData[rs.getRow() - 1][5] = rs.getString("store");
//            rowData[rs.getRow() - 1][6] = rs.getString("id");
//        }
//
//        table = new JTable(rowData,columnName) {
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        table.getTableHeader().setReorderingAllowed(false);   //不可整列移动
//        table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
//
//
//
//        db.closeConnection(con);
//
//        table.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                index = table.rowAtPoint(e.getPoint());
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
//    }

    JTextField Name = new JTextField();
    JTextField Category = new JTextField();
    JTextField Price = new JTextField();
    JTextField Discount = new JTextField();
    JTextField Portprice = new JTextField();
    JTextField Store = new JTextField();
    JTextField Id = new JTextField();
    //    选中行



    void addGoods() throws Exception {
//        界面设置
        JFrame frame = new JFrame("添加商品");
        frame.setSize(400, 300);
        frame.setResizable(false);

//        面板设置
        JPanel root = new JPanel();
        frame.setContentPane(root);
        root.setLayout(null);

        JLabel name = new JLabel("商品名:");
        name.setBounds(20, 15, 60, 30);
        root.add(name);
        Name.setBounds(80, 19, 110, 25);
        root.add(Name);

        JLabel category = new JLabel("类别:");
        category.setBounds(210, 15, 60, 30);
        root.add(category);
        Category.setBounds(260, 19, 110, 25);
        root.add(Category);

        JLabel price = new JLabel("单价:");
        price.setBounds(20, 60, 60, 30);
        root.add(price);
        Price.setBounds(80, 64, 110, 25);
        root.add(Price);

        JLabel discount = new JLabel("折扣:");
        discount.setBounds(210, 60, 60, 30);
        root.add(discount);
        Discount.setBounds(260, 64, 110, 25);
        root.add(Discount);

        JLabel portprice = new JLabel("进价:");
        portprice.setBounds(20, 105, 60, 30);
        root.add(portprice);
        Portprice.setBounds(80, 109, 110, 25);
        root.add(Portprice);

        JLabel store = new JLabel("库存:");
        store.setBounds(210, 105, 60, 30);
        root.add(store);
        Store.setBounds(260, 109, 110, 25);
        root.add(Store);

        JLabel id = new JLabel("商品ID:");
        id.setBounds(20, 150, 60, 30);
        root.add(id);
        Id.setBounds(80, 154, 110, 25);
        root.add(Id);

//        按钮设置
        JButton yes = new JButton("添加");
        yes.addActionListener((e) -> {
            try {
                add();
                //这里还需要一个增加商品后的刷新
                table = new AdminServiceImpl().flashForm(table);
                frame.dispose();
                showError.showError("添加商品", "添加成功");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        yes.setBounds(120, 200, 60, 30);
        root.add(yes);
        JButton no = new JButton("取消");
        no.addActionListener((e) -> {
            frame.dispose();
        });
        no.setBounds(215, 200, 60, 30);
        root.add(no);

//        窗口位置居中
        int[] location = getMiddlelocation.getMiddlelocate(frame);
        frame.setLocation(location[0], location[1]);

        frame.setVisible(true);
    }

    void add() throws Exception {
        String sql = "insert into goods values ('"+Name.getText()+"', '"+Category.getText()+"', "+ Price.getText()+", "+Discount.getText()+", "+Portprice.getText()+", "+Store.getText()+", "+Id.getText()+")";
        PstmtUtil pst = new PstmtUtil();
        PreparedStatement pre = pst.PstmtUtil(sql);
        int num = pre.executeUpdate(sql);
        System.out.println("成功添加" + num + "条商品");
        pst.closeConnection();
        pre.close();
        Name.setText("");
        Category.setText("");
        Price.setText("");
        Discount.setText("");
        Portprice.setText("");
        Store.setText("");
        Id.setText("");
    }

}
