package org.example.util;

import javax.swing.*;
import java.awt.*;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/3/20 19:16
 */

public class getMiddlelocation {




    /*
     * @Author Langxecho
            * @Description //TODO 返回一个数组，数组里装的是屏幕中央坐标的x和y
     * @Date 19:19 2023/3/20
     * @Param 
            * @return 
            **/
    public static int[] getMiddlelocate(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //获取到屏幕尺寸
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);   //居中位置就是：屏幕尺寸/2 - 窗口尺寸/2
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        int []a = new int[]{x,y};
        return a;
    }
}
