package com.lxz.frames;

import javax.swing.*;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.CloudController;
import com.lxz.entity.Administrator;
import com.lxz.entity.CloudFactory;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class FactoryJPanel extends JPanel {
    private JTextField factoryName;
    private JTable table;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private CloudController cloudController=new CloudController();

    /**
     * Create the panel.
     */
    public FactoryJPanel() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("工厂名称");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
        lblNewLabel.setBounds(65, 36, 92, 28);
        add(lblNewLabel);
        
        factoryName = new JTextField();
        factoryName.setBounds(65, 77, 266, 34);
        add(factoryName);
        factoryName.setColumns(10);
        
        JButton btnNewButton = new JButton("查询");
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    CloudFactory cloudFactory=(CloudFactory) cloudController.searchFactory(factoryName.getText());
                    if (cloudFactory==null){
                        JOptionPane.showMessageDialog(null,"输入错误，没有该工厂");
                        return;
                    }else {
                        //清空表格中的信息
                        defaultTableModel.setRowCount(0);
                        //显示查询后的信息
                        addTableRow(cloudFactory);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(527, 76, 113, 35);
        add(btnNewButton);
        
        table = new JTable(defaultTableModel);
        table.setBounds(33, 191, 779, 309);
        
        defaultTableModel.addColumn("工厂名称");
        defaultTableModel.addColumn("工厂简介");
        defaultTableModel.addColumn("负责人");
        defaultTableModel.addColumn("联系方式");
        defaultTableModel.addColumn("登录账号");
        defaultTableModel.addColumn("工作状态");
        
        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(14, 242, 792, 295);
        add(scollPane);
        
        JButton btnNewButton_1 = new JButton("停用");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int find = JOptionPane.showConfirmDialog(null, "确认修改？？", " 提示 ", JOptionPane.YES_NO_OPTION);
                if(find==0){
                    int num=table.getSelectedRow();
                    if(num>=table.getRowCount()||num<0){
                        JOptionPane.showMessageDialog(null,"请选择你要停用的工厂");
                        return;
                    }
                    //判断该工厂现在是否为开启状态
                    if(defaultTableModel.getValueAt(num, 5).toString().equals("正常")){
                        boolean success=false;
                        try {
                            success=cloudController.modifyStatus(defaultTableModel.getValueAt(num,4).toString());
                            if (success){
                                //清空操作
                                defaultTableModel.setRowCount(0);
                                showMessage();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"工厂现在为停用状态，操作错误！！！");
                    }
                }
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_1.setBounds(33, 143, 113, 27);
        add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("开启");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int find = JOptionPane.showConfirmDialog(null, "确认修改？？", " 提示 ", JOptionPane.YES_NO_OPTION);
                if(find==0){
                    int num=table.getSelectedRow();
                    if(num>=table.getRowCount()||num<0){
                        JOptionPane.showMessageDialog(null,"请选择你要停用的工厂");
                        return;
                    }
                    //判断该工厂现在是否为开启状态
                    if(defaultTableModel.getValueAt(num, 5).toString().equals("停用")){
                        boolean success=false;
                        try {
                            success=cloudController.modifyStatus(defaultTableModel.getValueAt(num,4).toString());
                            if (success){
                                //清空操作
                                defaultTableModel.setRowCount(0);
                                showMessage();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"工厂现在为正常状态，操作错误！！！");
                    }
                }
            }
        });
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_2.setBounds(206, 143, 113, 27);
        add(btnNewButton_2);

        try {
            showMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 展示管理员信息
    public void showMessage() throws IOException {

        List<Object> objects = cloudController.getFactory();
        for (int i = 0; i < objects.size(); i++) {
            addTableRow((CloudFactory) objects.get(i));
        }
    }

    // 添加一行表格信息
    public void addTableRow(CloudFactory factory) {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        // 工厂名称
        rowData.add(factory.getFactoryNameString());
        // 工厂描述
        rowData.add(factory.getFactoryDescString());
        // 负责人
        rowData.add(factory.getPrincipal());
        //联系方式
        rowData.add(factory.getLinkway());
        // 登录账号
        rowData.add(factory.getAccountNumber());
        // 工厂状态
        if(factory.isStatus()){
            rowData.add("正常");
        }else {
            rowData.add("停用");
        }
        // 添加一行
        defaultTableModel.addRow(rowData); // 添加一行

    }
}
