package org.example.util;

import javax.swing.*;

/**
 * @ClassName showError
 * @Description TODO
 * @Author BC
 * @Date 2023/3/21 20:31
 * @Version 1.0
 */
public class showError {
    public void showError(String title, String content) {
//        界面设置
        JFrame error = new JFrame();
        error.setSize(250, 150);
        error.setTitle(title);
        error.setResizable(false);

//        容器设置
        JPanel root = new JPanel();
        error.setContentPane(root);
        error.setLayout(null);

//        输入错误提示
        JLabel label = new JLabel(content);
        label.setBounds(-8, 9, 250, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        root.add(label);

//        确定按钮
        JButton button = new JButton("确定");
        button.setBounds(87, 65, 60, 27);
        button.addActionListener((e) -> {
            error.dispose();
        });
        root.add(button);

//        窗口位置居中
        int[] location =  getMiddlelocation.getMiddlelocate(error);
        error.setLocation(location[0], location[1]);

        error.setVisible(true);
    }
}
