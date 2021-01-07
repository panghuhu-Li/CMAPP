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

/**
 * @program: CMAPP
 * @description 租用功能实现面板
 * @author: 李星泽
 * @create: 2020-07-18 15:19
 **/
public class SuperJFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    // 单例模式避免选择不同功能时产生多个窗口
    private static SuperJFrame instanceofJFrame = null;

    public static SuperJFrame creatInstance() {
        if (instanceofJFrame == null) {
            instanceofJFrame = new SuperJFrame();
            return instanceofJFrame;
        } else {
            return instanceofJFrame;
        }
    }

    /**
     * 创建超级管理员窗口
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

            changeJFrame("用户管理", new UserManaJPanel());

        });
        menu1.add(mntmNewMenuItem);
        menuBar.add(menu2);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("云工厂信息");
        mntmNewMenuItem_1.addActionListener(e -> {

            changeJFrame("云工厂信息管理", new FactoryJPanel());

        });
        menu2.add(mntmNewMenuItem_1);
        menuBar.add(menu3);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("产品类别管理");
        mntmNewMenuItem_2.addActionListener(e -> {
            changeJFrame("产品类别管理", new ProductTypeJPanel());

        });
        menu3.add(mntmNewMenuItem_2);

        JSeparator separator_1 = new JSeparator();
        menu3.add(separator_1);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("产品信息管理");
        mntmNewMenuItem_3.addActionListener(e -> {

            changeJFrame("产品信息管理", new ProductInfoJPanel());

        });
        menu3.add(mntmNewMenuItem_3);
        menuBar.add(menu4);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("设备类型管理");
        mntmNewMenuItem_4.addActionListener(e -> {

            changeJFrame("设备管理", new EquipmentTypeJPanel());

        });
        menu4.add(mntmNewMenuItem_4);

        JSeparator separator_3 = new JSeparator();
        menu4.add(separator_3);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("数据字典管理");
        mntmNewMenuItem_5.addActionListener(e -> {

            changeJFrame("数据字典界面", new DataDictionaryJPanel());
//            changeJFrame("数据字典界面", new EquipmentJPanel());

        });
        menu4.add(mntmNewMenuItem_5);
        menuBar.add(menu5);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("订单基本信息");
        menu5.add(mntmNewMenuItem_6);
        this.setJMenuBar(menuBar);
        changeContenePane(new SuperJPanel());

    }

    // 改变面板
    public void changeJFrame(String titleString, JPanel jpanle) {
        SuperJFrame superJFrame = SuperJFrame.creatInstance();
        superJFrame.setVisible(true);
        setTitle(titleString);
        superJFrame.setBounds(100, 100, 850, 620);
        superJFrame.changeContenePane(jpanle);
        superJFrame.setLocationRelativeTo(null);
        superJFrame.setResizable(false);
    }

    public void changeContenePane(Container contentPane) {
        setContentPane(contentPane);
        // 重新启动
        this.revalidate();
    }
}

class SuperJPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * 创建超级管理员信息选择面板，目的是为了添加图片
     */
    public SuperJPanel() {
        setLayout(null);
        ImageIcon imageIcon = new ImageIcon("img/superJPanel.jpg");
        JLabel lb = new JLabel(imageIcon);
        lb.setBounds(0, 0, 611, 457);
        add(lb);
    }
}