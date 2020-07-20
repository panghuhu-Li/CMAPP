package com.lxz.frames;

import javax.swing.*;
import java.awt.Font;

import com.lxz.controllers.Adminidtratorcontrollers;
import com.lxz.entity.Agency;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginJPane extends JPanel {
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Create the panel.
     */
    public LoginJPane() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("账号");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        lblNewLabel.setBounds(79, 119, 106, 33);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("密码");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        lblNewLabel_1.setBounds(79, 211, 106, 33);
        add(lblNewLabel_1);

        JButton btnNewButton = new JButton("注册");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                LoginJFrame loginJFrame = LoginJFrame.creatInstance();
                loginJFrame.setVisible(true);
                loginJFrame.setBounds(100, 100, 990, 535);
                ;
                loginJFrame.changeContenePane(new RegisterJPanel());
                loginJFrame.setLocationRelativeTo(null);
                loginJFrame.setResizable(false);
            }
        });
        btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        btnNewButton.setBounds(79, 341, 106, 33);
        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("登录");
        btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String accountNumber = textField.getText();
                String password = new String(passwordField.getPassword());
                Adminidtratorcontrollers adminidtratorcontrollers = new Adminidtratorcontrollers();
                String isType = "";
                try {
                    isType = adminidtratorcontrollers.whoRegister(accountNumber, password);
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                if (isType.equals("wrong")) {
                    JOptionPane.showMessageDialog(null, "信息输入错误" +
                            "若还没有账号点击下方创建一个");
                    textField.setText("");
                    passwordField.setText("");
                    textField.requestFocus();
                    passwordField.requestFocus();
                    clearText();
                    return;
                }
                //若账号密码正确判断该管理员是什么类型
                String[] limits=isType.split("_");
                if (limits[0].equals("success" + "超级管理员")) {
                    SuperJFrame superJFrame = SuperJFrame.creatInstance();
                    changeJFrame(superJFrame);
                }
                if (limits[0].equals("success" + "经销商")) {
                    AgencyJFrame agencyJFrame = AgencyJFrame.creatInstance();
                    changeJFrame(agencyJFrame);
                }
                if (limits[0].equals("success" + "云工厂")) {
                    FactoryAdminiJFrame factoryAdminiJFrame = FactoryAdminiJFrame.creatInstance(limits[1]);
                    changeJFrame(factoryAdminiJFrame);
                }
            }
        });
        btnNewButton_1.setBounds(450, 341, 106, 33);
        add(btnNewButton_1);

        textField = new JTextField();
        textField.setBounds(275, 126, 238, 24);
        add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(275, 218, 238, 24);
        add(passwordField);

        JLabel lblNewLabel_2 = new JLabel("欢迎进入云智造登录界面");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setForeground(Color.BLACK);
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.ITALIC, 30));
        lblNewLabel_2.setBounds(32, 23, 563, 83);
        add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("没有账号？立即注册");
        lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 20));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setForeground(Color.RED);
        lblNewLabel_3.setBounds(79, 295, 197, 33);
        add(lblNewLabel_3);

        ImageIcon imageIcon = new ImageIcon("img/login.jpg");
        JLabel lb = new JLabel(imageIcon);
        lb.setBounds(0, 0, 609, 455);
        add(lb);
    }

    //单例模式改变窗口
    public void changeJFrame(JFrame jFrame){
        jFrame.setVisible(true);
        jFrame.setBounds(100, 100, 611, 457);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
    }

    public void clearText(){
        textField.setText("");
        passwordField.setText("");
        textField.requestFocus();
    }
}
