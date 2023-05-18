package org.example.util;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName review
 * @Description TODO
 * @Author BC
 * @Date 2023/3/25 12:53
 * @Version 1.0
 */
public class reviewContent {
    public static JPanel review() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.pink);

        JLabel name = new JLabel("用户名:");
        panel.add(name);
        JLabel id = new JLabel("ID:");
        panel.add(id);
        return panel;
    }
}
