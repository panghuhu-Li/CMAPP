package com.lxz.frames;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.Adminidtratorcontrollers;
import com.lxz.controllers.CloudController;
import com.lxz.entity.Administrator;
import com.lxz.entity.Agency;
import com.lxz.entity.CloudAdministrator;
import com.lxz.entity.CloudFactory;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

/**
 * @program: CMAPP
 * @description 租用功能实现面板
 * @author: 李星泽
 * @create: 2020-07-19 10:03
 **/
public class UserManaJPanel extends JPanel {
    private String factoryType;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private JTextField accountNumber;
    private JTextField name;
    private JTextField linkWay;
    private JPasswordField passwordField;
    private JTextField factoryName;
    private JTextField factoryDesc;
    private JTable table;
    private Adminidtratorcontrollers adminidtratorcontrollers = new Adminidtratorcontrollers();

    /**
     * Create the panel.
     */
    public UserManaJPanel() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("登录账号");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel.setBounds(42, 31, 72, 18);
        add(lblNewLabel);

        accountNumber = new JTextField();
        accountNumber.setBounds(174, 28, 165, 24);
        add(accountNumber);
        accountNumber.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("登录密码");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_1.setBounds(42, 89, 72, 18);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("真实姓名");
        lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_2.setBounds(401, 31, 72, 18);
        add(lblNewLabel_2);

        name = new JTextField();
        name.setBounds(546, 28, 165, 24);
        add(name);
        name.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("联系方式");
        lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_3.setBounds(401, 89, 72, 18);
        add(lblNewLabel_3);

        linkWay = new JTextField();
        linkWay.setBounds(546, 86, 165, 24);
        add(linkWay);
        linkWay.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(174, 86, 165, 24);
        add(passwordField);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("云工厂");
        comboBox.addItem("经销商");
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                factoryType = comboBox.getSelectedItem().toString();
                if (factoryType==null||factoryType.equals("经销商")||factoryType.equals("")) {
                    factoryDesc.setEditable(false);
                    factoryDesc.setBackground(Color.LIGHT_GRAY);
                    factoryName.setEditable(false);
                    factoryName.setBackground(Color.LIGHT_GRAY);
                }else if(factoryType.equals("云工厂")) {
                    factoryDesc.setEditable(true);
                    factoryName.setEditable(true);
                }
            }
        });
        comboBox.setBounds(174, 150, 86, 24);
        add(comboBox);

        JLabel lblNewLabel_4 = new JLabel("注册类型");
        lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_4.setBounds(42, 153, 72, 18);
        add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("工厂名称");
        lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_5.setBounds(401, 153, 72, 18);
        add(lblNewLabel_5);

        factoryName = new JTextField();
        factoryName.setBounds(546, 150, 165, 24);
        add(factoryName);
        factoryName.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("工厂简介");
        lblNewLabel_6.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_6.setBounds(14, 211, 72, 18);
        add(lblNewLabel_6);

        factoryDesc = new JTextField();
        factoryDesc.setBounds(111, 208, 165, 24);
        add(factoryDesc);
        factoryDesc.setColumns(10);

        JButton btnNewButton = new JButton("新建");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (factoryType == null || factoryType.equals("")) {
                    JOptionPane.showMessageDialog(null, "请选择你要创建的类型");
                    return;
                }
                // 添加云工厂管理员
                if (factoryType.equals("云工厂")) {
                    CloudAdministrator cloudAdministrator = new CloudAdministrator();
                    CloudFactory cloudFactory = new CloudFactory();
                    // 设置账号
                    if (accountNumber.getText() == null || accountNumber.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "账号不能为空");
                        return;
                    }
                    cloudAdministrator.setaccountNumber(accountNumber.getText());
                    cloudFactory.setAccountNumber(accountNumber.getText());

                    // 设置密码
                    String pw = new String(passwordField.getPassword());
                    if (pw == null || pw.equals("")) {
                        JOptionPane.showMessageDialog(null, "密码不能为空");
                        return;
                    }
                    cloudAdministrator.setPassword(pw);

                    // 设置姓名
                    if (name.getText() == null || name.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "真实姓名不能为空");
                        return;
                    }
                    cloudAdministrator.setName(name.getText());
                    cloudFactory.setPrincipal(name.getText());
                    cloudFactory.setLinkman(name.getText());

                    // 设置联系方式
                    if (linkWay.getText() == null || linkWay.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "联系方式不能为空");
                        return;
                    }
                    cloudAdministrator.setContactWay(linkWay.getText());
                    cloudFactory.setLinkway(linkWay.getText());
                    // 设置工厂类型
                    cloudAdministrator.setRegisterType(factoryType);

                    // 设置工厂名称
                    if (factoryName.getText() == null || factoryName.getText().equals("")) {
                        cloudAdministrator.setFactorName("无");
                        cloudFactory.setFactoryNameString("无");
                    } else {
                        cloudAdministrator.setFactorName(factoryName.getText());
                        cloudFactory.setFactoryNameString(factoryName.getText());
                    }
                    // 初始化云工厂运行状态为关闭
                    cloudFactory.setStatus(false);

                    // 设置工厂描述
                    if (factoryDesc.getText() == null || factoryDesc.getText().equals("")) {
                        cloudAdministrator.setFactorDesc("无");
                        cloudFactory.setFactoryNameString("无");
                    } else {
                        cloudAdministrator.setFactorDesc(factoryDesc.getText());
                        cloudFactory.setFactoryDescString(factoryDesc.getText());
                    }

                    // 判断管理员是否添加成功
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
                    // 只有云工厂管理员添加成功工厂才能创建成功
                    if (ifSuccess) {
                        CloudController cloudController = new CloudController();
                        // 判断云工厂是否添加成功
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

                // 添加经销商
                if (factoryType.equals("经销商")) {
                    Agency agency = new Agency();
                    // 设置账号
                    agency.setaccountNumber(accountNumber.getText());
                    // 设置密码
                    String pw = new String(passwordField.getPassword());
                    agency.setPassword(pw);
                    // 设置姓名
                    agency.setName(name.getText());
                    // 设置联系方式
                    agency.setContactWay(linkWay.getText());
                    // 设置工厂类型
                    agency.setRegisterType(factoryType);
                    // 添加经销商

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

                try {
                    // 清楚原表格中的信息
                    defaultTableModel.setRowCount(0);
                    // 展示添加后的信息
                    showMessage();
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton.setBounds(290, 205, 113, 27);
        add(btnNewButton);

        JButton button = new JButton("删除");
        button.addActionListener(arg0 -> {
            int find = JOptionPane.showConfirmDialog(null, "是否确定要删除该用户", "温馨提示", JOptionPane.YES_NO_OPTION);
            if (find == 0) {
                //获得选中的行
                int num = table.getSelectedRow();
                if (num < 0 || num > table.getRowCount() - 1) {
                    JOptionPane.showMessageDialog(null, "请选择你要删除的行");
                    return;
                }
                try {
                    //获得选中行中对应的信息
                    adminidtratorcontrollers.delete(defaultTableModel.getValueAt(num, 0).toString());
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                //一定放在最后  因为删除后行的编号就会发生变化
                defaultTableModel.removeRow(num);
            }
        });
        button.setFont(new Font("宋体", Font.BOLD, 20));
        button.setBounds(428, 205, 113, 27);
        add(button);

        table = new JTable(defaultTableModel);
        table.setBounds(14, 242, 792, 295);
        table.setRowSelectionAllowed(true); // 整行选择

        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(14, 242, 792, 295);
        add(scollPane);

        JLabel lblNewLabel_7 = new JLabel("输入真实姓名进行查找，若修改相应信息请在对应的输入框中填写新信息");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_7.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_7.setForeground(Color.RED);
        lblNewLabel_7.setBounds(283, 184, 512, 18);
        add(lblNewLabel_7);

        JButton btnNewButton_1 = new JButton("检索");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // 获取检索后的管理员对象
                Administrator administrator = null;
                try {
                    //姓名框输入不能为空
                    if (name.getText().equals("") || name.getText() == null) {
                        JOptionPane.showMessageDialog(null, "查找的姓名不能为空");
                    } else {
                        administrator = (Administrator) adminidtratorcontrollers.searchAdministrator(name.getText());
                    }
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                if (administrator == null) {
                    JOptionPane.showMessageDialog(null, "不存在该管理员");
                    return;
                }

                // 清楚原表格中的信息
                defaultTableModel.setRowCount(0);
                // 展示添加后的信息
                addTableRow(administrator);
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_1.setBounds(555, 207, 113, 27);
        add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("修改");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int find = JOptionPane.showConfirmDialog(null, "确认修改？？", " 提示 ", JOptionPane.YES_NO_OPTION);
                if (find == 0) {
                    int num = table.getSelectedRow();
                    if (num >= table.getRowCount() || num < 0) {
                        JOptionPane.showMessageDialog(null, "请选择你要修改的管理员");
                        return;
                    }
                    String account = defaultTableModel.getValueAt(num, 0).toString();
                    String changeName;
                    String changeLinkWayString;
                    if (name.getText() == null || name.getText().equals("")) {
                        changeName = defaultTableModel.getValueAt(num, 1).toString();
                    } else {
                        changeName = name.getText();
                    }
                    if (linkWay.getText() == null || linkWay.getText().equals("")) {
                        changeLinkWayString = defaultTableModel.getValueAt(num, 2).toString();
                    } else {
                        changeLinkWayString = linkWay.getText();
                    }

                    boolean success = false;
                    try {
                        //通过账户添加改变的姓名和联系方式
                        success = adminidtratorcontrollers.modifyAdministrator(account, changeName,
                                changeLinkWayString);
                        if (success) {
                            JOptionPane.showMessageDialog(null, "修改成功");
                            // 清楚原表格中的信息
                            defaultTableModel.setRowCount(0);
                            // 展示添加后的信息
                            showMessage();
                        } else {
                            JOptionPane.showMessageDialog(null, "修改失败，该管理员可能不存在");
                        }
                    } catch (IOException e1) {
                        // TODO 自动生成的 catch 块
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_2.setBounds(682, 207, 113, 27);
        add(btnNewButton_2);

        defaultTableModel.addColumn("登录账号");
        defaultTableModel.addColumn("姓名");
        defaultTableModel.addColumn("联系方式");
        defaultTableModel.addColumn("角色名称");

        try {
            showMessage();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

    // 展示管理员信息
    public void showMessage() throws IOException {

        List<Object> objects = adminidtratorcontrollers.getAdministrator();
        for (int i = 0; i < objects.size(); i++) {
            addTableRow((Administrator) objects.get(i));
        }
    }

    // 添加一行表格信息
    public void addTableRow(Administrator administrator) {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        // 登录账号
        rowData.add(administrator.getaccountNumber());
        // 姓名
        rowData.add(administrator.getName());
        // 联系方式
        rowData.add(administrator.getContactWay());
        // 角色名称
        rowData.add(administrator.getRegisterType());
        // 添加一行
        defaultTableModel.addRow(rowData); // 添加一行
    }
}
