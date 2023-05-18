package org.example.frame;


import org.example.util.getMiddlelocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @Author Langxecho
 * @Description //TODO 用户评论查看ui
 * @Date 18:52 2023/3/20
 **/
public class ReviewUI {
    JFrame review = new JFrame();//外层窗口
    ScrollPane scro = new ScrollPane();//评论区大滚动面板
    JPanel inner = new JPanel();//滚动面板内的展示面板
    JFrame addreview = new JFrame("添加评论");
    JButton backbotton = new JButton("返回");//返回按钮
    JButton addreviewbotton = new JButton("添加评论");//添加评论
    JTextArea input = new JTextArea();//评论区评论框
    JButton reviewback = new JButton("返回");//添加评论界面的返回按钮
    JButton submit = new JButton("提交评论");//添加评论界面的提交按钮


    /*
     * @Author Langxecho
            * @Description //TODO 生成窗口，返回一个对象，该对象为评论窗口
     * @Date 20:26 2023/3/20
     * @Param
            * @return
            **/
public JFrame generatereview(){

    review.setSize(800,600);
    review.setTitle("评论区");
    review.setLayout(null);
    int[] a = getMiddlelocation.getMiddlelocate(review);
    review.setLocation(a[0],a[1]);

    scro.setSize(750,500);
    scro.setLocation(17,10);
//  scro.setBackground(Color.BLUE);
    review.add(scro);
    //最外层滚动面板完成
    //内部展示面板开始
    GridLayout gridLayout = new GridLayout();
    gridLayout.setColumns(1);
    gridLayout.setRows(100);//最大评论数
    inner.setLayout(gridLayout);
    scro.add(inner);
    //内部展示面板结束
    //生成内部部件
    generatebackbotton();
    generateaddreviewbotton();
    review.setVisible(true);

    return review;
}
/*
 * @Author Langxecho
        * @Description //TODO 生成评论区的评论模块(添加一条新的评论)，添加至展示大面板中,填写的内容为评论内容
 * @Date 23:07 2023/3/20
 * @Param
        * @return
        **/
public JPanel reviewarea(String user,String text){
//评论模块制作开始
    JPanel reviewarea = new JPanel();
//    reviewarea.setLayout(null);
//    reviewarea.setSize();
    String userinfo = "用户：" + user;
    JLabel headline = new JLabel(userinfo);
    JLabel empty = new JLabel("                                                                                                                                                                        ");
    FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
    reviewarea.setLayout(flow);
//    reviewarea.setSize(748,50);
    reviewarea.setPreferredSize(new Dimension(700,60));
    reviewarea.add(headline);
    reviewarea.add(empty);
    headline.setLocation(20,20);
    inner.add(reviewarea);
    JLabel content = new JLabel("    "+"评论:" + text);
    reviewarea.add(content);
//    reviewarea.setBackground(Color.cyan);
    return reviewarea;
}
/*
 * @Author Langxecho
        * @Description //TODO 返回按钮的设置（关闭当前窗口）
 * @Date 19:52 2023/3/21
 * @Param
        * @return
        **/
JButton generatebackbotton(){
    backbotton.setSize(80,30);
    backbotton.setLocation(20,520);
    backbotton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            review.dispose();
        }
    });
    review.add(backbotton);
    return backbotton;
}
/*
 * @Author Langxecho
        * @Description //TODO 生成添加评论按钮
 * @Date 20:17 2023/3/21
 * @Param
        * @return
        **/
JButton generateaddreviewbotton(){
    addreviewbotton.setSize(100,30);
    addreviewbotton.setLocation(110,520);
    review.add(addreviewbotton);
    addreviewbotton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //这里添加评论模块
            addreview.setSize(400,300);
            int a[] = getMiddlelocation.getMiddlelocate(addreview);
            addreview.setLocation(a[0],a[1]);
            addreview.setLayout(new FlowLayout(FlowLayout.LEFT));
        //评论文本区域的设置
            input.setPreferredSize(new Dimension(380,200));
            addreview.add(input);
            addreview.setVisible(true);
            //返回按钮的设置
            reviewback.setSize(80,30);
            reviewback.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addreview.dispose();
                }
            });
            addreview.add(reviewback);
            //提交评论按钮的添加
            submit.setSize(80,30);
            addreview.add(submit);
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String str = input.getText();
                    System.out.println(str);
//                    这里的监听器用来返回新加评论的内容
                }
            });
        }
    });
    return addreviewbotton;

}

}
