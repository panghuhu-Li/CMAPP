package com.lxz.frames;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class AgencyJFrame extends JFrame {
    //单例模式
    private static AgencyJFrame instanceofJFrame = null;

    /**
     * Create the frame.
     */
    private AgencyJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 457);
        setLocationRelativeTo(null);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("订单管理");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("我的订单");
        mnNewMenu.add(mntmNewMenuItem);
        
        changeContenePane(new AgencyJPanel());
    }

    public static AgencyJFrame creatInstance() {
        if (instanceofJFrame == null) {
            instanceofJFrame = new AgencyJFrame();
            return instanceofJFrame;
        } else {
            return instanceofJFrame;
        }
    }

    public void changeContenePane(Container contentPane) {
        setContentPane(contentPane);
        // 重新启动
        this.revalidate();
    }
}
class AgencyJPanel extends JPanel {

    /**
     * Create the panel.
     */
    public AgencyJPanel() {
        setLayout(null);
        ImageIcon imageIcon = new ImageIcon("img/superJPanel.jpg");
        JLabel lb = new JLabel(imageIcon);
        lb.setBounds(0, 0, 611, 457);
        add(lb);

    }

}
