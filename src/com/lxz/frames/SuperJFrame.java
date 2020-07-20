package com.lxz.frames;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class SuperJFrame extends JFrame {

    private static SuperJFrame instanceofJFrame = null;

    /**
     * Create the frame.
     */
    private SuperJFrame() {

        setLocationRelativeTo(null);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("系统设置");
        JMenu menu2 = new JMenu("云工厂");
        JMenu menu3 = new JMenu("产品管理");
        JMenu menu4 = new JMenu("产能中心");
        JMenu menu5 = new JMenu("订单管理");
        menuBar.add(menu1);

        JMenuItem mntmNewMenuItem = new JMenuItem("用户管理");
        mntmNewMenuItem.addActionListener(arg0 -> {
            SuperJFrame superJFrame = SuperJFrame.creatInstance();
            superJFrame.setVisible(true);
            superJFrame.setBounds(100, 100, 850, 620);
            superJFrame.changeContenePane(new UserManaJPanel());
            superJFrame.setLocationRelativeTo(null);
            superJFrame.setResizable(false);

        });
        menu1.add(mntmNewMenuItem);
        menuBar.add(menu2);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("云工厂信息");
        mntmNewMenuItem_1.addActionListener(e -> {
            SuperJFrame superJFrame = SuperJFrame.creatInstance();
            superJFrame.setVisible(true);
            setTitle("云工厂信息管理");
            superJFrame.setBounds(100, 100, 850, 620);
            superJFrame.changeContenePane(new FactoryJPanel());
            superJFrame.setLocationRelativeTo(null);
            superJFrame.setResizable(false);

        });
        menu2.add(mntmNewMenuItem_1);
        menuBar.add(menu3);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("产品类别管理");
        mntmNewMenuItem_2.addActionListener(e -> {
            // TODO 自动生成的方法存根

        });
        menu3.add(mntmNewMenuItem_2);

        JSeparator separator_1 = new JSeparator();
        menu3.add(separator_1);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("产品信息管理");
        mntmNewMenuItem_3.addActionListener(e -> {
            SuperJFrame superJFrame = SuperJFrame.creatInstance();
            superJFrame.setVisible(true);
            setTitle("产品信息管理");
            superJFrame.setBounds(100, 100, 850, 620);
            superJFrame.changeContenePane(new ProductInfoJPanel());
            superJFrame.setLocationRelativeTo(null);
            superJFrame.setResizable(false);
        });
        menu3.add(mntmNewMenuItem_3);
        menuBar.add(menu4);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("设备类型管理");
        menu4.add(mntmNewMenuItem_4);

        JSeparator separator_3 = new JSeparator();
        menu4.add(separator_3);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("设备管理");
        mntmNewMenuItem_5.addActionListener(e -> {

            SuperJFrame superJFrame = SuperJFrame.creatInstance();
            superJFrame.setVisible(true);
            setTitle("设备类型管理");
            superJFrame.setBounds(100, 100, 850, 620);
            superJFrame.changeContenePane(new EquipmentJPanel());
            superJFrame.setLocationRelativeTo(null);
            superJFrame.setResizable(false);

        });
        menu4.add(mntmNewMenuItem_5);
        menuBar.add(menu5);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("订单基本信息");
        menu5.add(mntmNewMenuItem_6);
        this.setJMenuBar(menuBar);
        changeContenePane(new SuperJPanel());



    }

    public static SuperJFrame creatInstance() {
        if (instanceofJFrame == null) {
            instanceofJFrame = new SuperJFrame();
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

class SuperJPanel extends JPanel {

    /**
     * Create the panel.
     */
    public SuperJPanel() {
        setLayout(null);
        ImageIcon imageIcon = new ImageIcon("img/superJPanel.jpg");
        JLabel lb = new JLabel(imageIcon);
        lb.setBounds(0, 0, 611, 457);
        add(lb);

    }

}