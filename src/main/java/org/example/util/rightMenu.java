package org.example.util;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * @ClassName rightSelected
 * @Description TODO
 * @Author BC
 * @Date 2023/3/23 8:38
 * @Version 1.0
 */
public class rightMenu extends JFrame {
    public static void rightMenu(JTable table, MouseEvent e, JPopupMenu menu) {
        int row = table.rowAtPoint(e.getPoint());
        if (row == -1) {
            return;
        }

        int[] rows = table.getSelectedRows();
        boolean inSelected = false;
        for (int r : rows) {
            if (row == r) {
                inSelected = true;
                break;
            }
        }

        if (!inSelected) {
            table.setRowSelectionInterval(row, row);
        }

        if (e.isMetaDown()) {
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
