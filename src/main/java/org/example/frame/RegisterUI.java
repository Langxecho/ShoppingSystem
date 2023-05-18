package org.example.frame;

import org.example.service.impl.UserServiceImpl;
import org.example.util.getMiddlelocation;
import org.example.util.showError;

import javax.swing.*;


public class RegisterUI extends JFrame{
    public static void showRegister() {
//        界面设置
        JFrame register = new JFrame();
        register.setSize(400, 300);
        register.setTitle("账户注册");
        register.setDefaultCloseOperation(EXIT_ON_CLOSE);
        register.setResizable(false);

//        容器设置
        JPanel root = new JPanel();
        register.setContentPane(root);
        root.setLayout(null);

//        输入用户名
        JLabel act = new JLabel("新用户名");
        act.setBounds(50, 35, 70, 30);
        root.add(act);

        JTextField Act = new JTextField();
        Act.setBounds(130, 35, 150, 25);
        root.add(Act);

//        输入密码
        JLabel psd = new JLabel("新密码");
        psd.setBounds(50, 75, 70, 30);
        root.add(psd);

        JTextField Psd = new JTextField();
        Psd.setBounds(130, 75, 150, 25);
        root.add(Psd);

//        重复密码
        JLabel psd2 = new JLabel("重复新密码");
        psd2.setBounds(50, 115, 70, 30);
        root.add(psd2);

        JTextField Psd2 = new JTextField();
        Psd2.setBounds(130, 115, 150, 25);
        root.add(Psd2);

//        注册按钮
        JButton button = new JButton("注册");
        button.setBounds(145, 170, 120, 30);
        button.addActionListener((e) -> {
          if(Psd.getText().equals(Psd2.getText())){
              try {
                  new UserServiceImpl().registe(Act.getText(),Psd.getText(),Psd2.getText());
                  showError.showError("success","恭喜您,注册成功");
                  register.dispose();
                  LoginUI ss = new LoginUI();
                  ss.showLogin();
              } catch (Exception ex) {
                  System.out.println("注册失败");
              }

          }
          else {showError.showError("出错","两次密码不一致");}
        });
        root.add(button);

//        返回按钮
        JButton button1 = new JButton("返回");
        button1.setBounds(300, 220, 60, 25);
        button1.addActionListener((e) -> {
            LoginUI ss = new LoginUI();
            ss.showLogin();
            register.dispose();
        });
        root.add(button1);

//        窗口位置居中
        int[] location = getMiddlelocation.getMiddlelocate(register);
        register.setLocation(location[0], location[1]);

        register.setVisible(true);
    }
}
