package com.lxz.frames;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AddOrderJFrame extends JFrame {

    private JPanel contentPane;
    //单例模式
    private static AddOrderJFrame instanceFrame = null;

    /**
     *
     * @return void
     * @description single module to creat object in order to maintain the only object
     */
    public static AddOrderJFrame creatInstance() {
        if (instanceFrame == null) {
            instanceFrame = new AddOrderJFrame();
            return instanceFrame;
        } else {
            return instanceFrame;
        }
    }


    /**
     * Create the frame.
     */
    public AddOrderJFrame() {
        setBounds(100, 100, 435, 659);
        setLocationRelativeTo(null);
        setResizable(true);
        changeContenePane(new AddOrderJPanel());
    }

    public void changeContenePane(Container contentPane) {
        setContentPane(contentPane);
        // 重新启动
        this.revalidate();
    }

    // 改变面板
    public void changeJFrame(String titleString, JPanel jpanle) {
        AddOrderJFrame addOrderJFrame = AddOrderJFrame.creatInstance();
        addOrderJFrame.setVisible(true);
        setTitle(titleString);
        addOrderJFrame.setBounds(100, 100, 435, 659);
        addOrderJFrame.changeContenePane(jpanle);
        addOrderJFrame.setLocationRelativeTo(null);
        addOrderJFrame.setResizable(false);
    }

}
