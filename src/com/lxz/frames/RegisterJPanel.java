package com.lxz.frames;

import com.lxz.controllers.Adminidtratorcontrollers;
import com.lxz.controllers.CloudController;
import com.lxz.entity.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.io.IOException;

/**
 * @program: CMAPP
 * @description 注册面板
 * @author: 李星泽
 * @create: 2020-07-17 21:43
 **/
public class RegisterJPanel extends JPanel{
    private JTextField factoryName;
    private JTextField factoryDesc;
    private JTextField name;
    private JTextField linkWay;
    private JTextField accountNumber;
    private JPasswordField passwordField;
    private String factoryType;
    
    JRadioButton rdbtn1 = null;
    JRadioButton rdbtn2 = null; 

    /**
     * 创建注册面板
     */
    public RegisterJPanel() {
        setLayout(null);


        JLabel lblNewLabel = new JLabel("登录账号");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel.setBounds(38, 43, 114, 27);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("登录密码");
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_1.setBounds(38, 110, 72, 18);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("真实姓名");
        lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_2.setForeground(Color.RED);
        lblNewLabel_2.setBounds(38, 173, 72, 18);
        add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("注册类型");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setForeground(Color.RED);
        lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_3.setBounds(38, 299, 72, 18);
        add(lblNewLabel_3);


        JLabel lblNewLabel_4 = new JLabel("工厂名称");
        lblNewLabel_4.setForeground(Color.RED);
        lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_4.setBounds(38, 350, 72, 38);
        add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("工厂简介");
        lblNewLabel_5.setForeground(Color.RED);
        lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_5.setBounds(38, 419, 72, 18);
        add(lblNewLabel_5);

        JButton btnNewButton = new JButton("确认");
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 15));
        btnNewButton.addActionListener(arg0 -> {
            if (factoryType == null || factoryType.equals("")) {
                JOptionPane.showMessageDialog(null, "请选择你要创建的类型");
                return;
            }
            //添加云工厂管理员
            if (factoryType.equals("云工厂")) {
                CloudAdministrator cloudAdministrator = new CloudAdministrator();
                CloudFactory cloudFactory = new CloudFactory();
                //设置账号
                if (accountNumber.getText() == null || accountNumber.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "账号不能为空");
                    return;
                }
                cloudAdministrator.setaccountNumber(accountNumber.getText());
                cloudFactory.setAccountNumber(accountNumber.getText());

                //设置密码
                String pw = new String(passwordField.getPassword());
                if (pw.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空");
                    return;
                }
                cloudAdministrator.setPassword(pw);

                //设置姓名
                if (name.getText() == null || name.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "真实姓名不能为空");
                    return;
                }
                cloudAdministrator.setName(name.getText());
                cloudFactory.setPrincipal(name.getText());
                cloudFactory.setLinkman(name.getText());

                //设置联系方式
                if (linkWay.getText() == null || linkWay.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "联系方式不能为空");
                    return;
                }
                cloudAdministrator.setContactWay(linkWay.getText());
                cloudFactory.setLinkway(linkWay.getText());
                //设置工厂类型
                cloudAdministrator.setRegisterType(factoryType);

                //设置工厂名称
                if (factoryName.getText() == null || factoryName.getText().equals("")) {
                    cloudAdministrator.setFactorName("无");
                    cloudFactory.setFactoryNameString("无");
                } else {
                    cloudAdministrator.setFactorName(factoryName.getText());
                    cloudFactory.setFactoryNameString(factoryName.getText());
                }
                //初始化云工厂运行状态为关闭
                cloudFactory.setStatus(false);

                //设置工厂描述
                if (factoryDesc.getText() == null || factoryDesc.getText().equals("")) {
                    cloudAdministrator.setFactorDesc("无");
                    cloudFactory.setFactoryNameString("无");
                } else {
                    cloudAdministrator.setFactorDesc(factoryDesc.getText());
                    cloudFactory.setFactoryDescString(factoryDesc.getText());
                }

                Adminidtratorcontrollers adminidtratorcontrollers = new Adminidtratorcontrollers();
                //判断管理员是否添加成功
                boolean ifSuccess = false;
                try {
                    ifSuccess = adminidtratorcontrollers.addAdministrator(cloudAdministrator);
                    if (ifSuccess) {
                        JOptionPane.showMessageDialog(null, "管理员创建成功，您的密码是" + pw);
                    } else {
                        JOptionPane.showMessageDialog(null, "创建失败,该账号已经存在");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //只有云工厂管理员添加成功工厂才能创建成功
                if (ifSuccess) {
                    CloudController cloudController = new CloudController();
                    //判断云工厂是否添加成功
                    boolean cloudAdd;
                    try {
                        cloudAdd = cloudController.add(cloudFactory);
                        if (cloudAdd) {
                            JOptionPane.showMessageDialog(null, "工厂添加成功");
                        } else {
                            JOptionPane.showMessageDialog(null, "添加失败");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                accountNumber.setText("");
                passwordField.setText("");
                name.setText("");
                linkWay.setText("");
                factoryDesc.setText("");
                factoryName.setText("");
            }

            //添加经销商
            if (factoryType.equals("经销商")) {
                Agency agency = new Agency();
                //设置账号
                agency.setaccountNumber(accountNumber.getText());
                //设置密码
                String pw = new String(passwordField.getPassword());
                agency.setPassword(pw);
                //设置姓名
                agency.setName(name.getText());
                //设置联系方式
                agency.setContactWay(linkWay.getText());
                //设置工厂类型
                agency.setRegisterType(factoryType);
                //添加经销商
                Adminidtratorcontrollers adminidtratorcontrollers = new Adminidtratorcontrollers();
                boolean ifSuccess;
                try {
                    ifSuccess = adminidtratorcontrollers.addAdministrator(agency);
                    if (ifSuccess) {
                        JOptionPane.showMessageDialog(null, "创建成功，您的密码是" + pw);
                    } else {
                        JOptionPane.showMessageDialog(null, "创建失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                accountNumber.setText("");
                passwordField.setText("");
                name.setText("");
                linkWay.setText("");
                factoryDesc.setText("");
                factoryName.setText("");
            }
        });
        btnNewButton.setBounds(482, 460, 113, 38);
        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("关闭");
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 15));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginJFrame loginJFrame = LoginJFrame.creatInstance();
                loginJFrame.setVisible(true);
                loginJFrame.setBounds(100, 100, 609, 455);
                loginJFrame.changeContenePane(new LoginJPane());
                loginJFrame.setLocationRelativeTo(null);
                loginJFrame.setResizable(false);
            }
        });
        btnNewButton_1.setBounds(721, 460, 113, 38);
        add(btnNewButton_1);

        factoryName = new JTextField();
        factoryName.setBounds(148, 357, 272, 24);
        add(factoryName);
        factoryName.setColumns(10);

        factoryDesc = new JTextField();
        factoryDesc.setBounds(148, 416, 272, 24);
        add(factoryDesc);
        factoryDesc.setColumns(10);

        name = new JTextField();
        name.setBounds(148, 170, 272, 24);
        add(name);
        name.setColumns(10);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("云工厂");
        comboBox.addItem("经销商");
        comboBox.addActionListener(e -> {
            factoryType = comboBox.getSelectedItem().toString();
            if (factoryType==null||factoryType.equals("经销商")||factoryType.equals("")) {
                factoryDesc.setEditable(false);
                factoryDesc.setBackground(Color.LIGHT_GRAY);
                factoryName.setEditable(false);
                factoryName.setBackground(Color.LIGHT_GRAY);
            }else if(factoryType.equals("云工厂")) {
                factoryDesc.setEditable(true);
                factoryDesc.setBackground(Color.WHITE);
                factoryName.setEditable(true);
                factoryName.setBackground(Color.WHITE);
            }
        });
        comboBox.setBounds(148, 296, 205, 24);
        add(comboBox);

        linkWay = new JTextField();
        linkWay.setBounds(148, 239, 272, 24);
        add(linkWay);
        linkWay.setColumns(10);

        accountNumber = new JTextField();
        accountNumber.setBounds(148, 44, 272, 24);
        add(accountNumber);
        accountNumber.setColumns(10);

        JLabel label = new JLabel("New label");
        label.setBounds(38, 296, 72, 18);
        add(label);

        JLabel lblNewLabel_6 = new JLabel("联系方式");
        lblNewLabel_6.setForeground(Color.RED);
        lblNewLabel_6.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_6.setBounds(38, 242, 96, 24);
        add(lblNewLabel_6);

        passwordField = new JPasswordField();
        passwordField.setBounds(148, 107, 272, 24);
        add(passwordField);

        ImageIcon imageIcon = new ImageIcon("img/background1.jpg");
        JLabel lb = new JLabel(imageIcon);
        lb.setFont(new Font("宋体", Font.BOLD, 15));
        lb.setBounds(0, 0, 991, 536);
        add(lb);

    }

}
