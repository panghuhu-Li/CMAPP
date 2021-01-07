package com.lxz.frames;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @program: CMAPP
 * @description 经销商窗口
 * @author: 李星泽
 * @create: 2020-07-18 15:23
 **/
public class AgencyJFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    // 单例模式
    private static AgencyJFrame instanceofJFrame = null;

    /**
     * 创建一个窗口
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
        mntmNewMenuItem.addActionListener(e -> {
            
            AgencyJFrame agencyJFrame=AgencyJFrame.creatInstance();
            agencyJFrame.changeJFrame("订单发布", new OrderJPanel());
            
        });
        mnNewMenu.add(mntmNewMenuItem);

        changeContenePane(new AgencyJPanel());
    }

    // 改变面板
    public void changeJFrame(String titleString, JPanel jpanle) {
        AgencyJFrame agencyJFrame=AgencyJFrame.creatInstance();
        agencyJFrame.setVisible(true);
        setTitle(titleString);
        agencyJFrame.setBounds(100, 100, 850, 620);
        agencyJFrame.changeContenePane(jpanle);
        agencyJFrame.setLocationRelativeTo(null);
        agencyJFrame.setResizable(false);
    }

    /**
     * @param contentPane:需要改变的面板
     */

    public void changeContenePane(Container contentPane) {
        setContentPane(contentPane);
        // 重新启动
        this.revalidate();
    }

    // 单例模式
    public static AgencyJFrame creatInstance() {
        if (instanceofJFrame == null) {
            instanceofJFrame = new AgencyJFrame();
            return instanceofJFrame;
        } else {
            return instanceofJFrame;
        }
    }

}

/**
 * 经销商面板
 */
class AgencyJPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * 创建经销商面板
     */
    public AgencyJPanel() {
        setLayout(null);
        ImageIcon imageIcon = new ImageIcon("img/superJPanel.jpg");
        JLabel lb = new JLabel(imageIcon);
        lb.setBounds(0, 0, 611, 457);
        add(lb);
    }
}
