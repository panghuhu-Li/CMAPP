package com.lxz.frames;

import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.TenderController;
import com.lxz.entity.Tender;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgenceTenderInfoJPanel extends JPanel {
    private JTable table;
    private final DefaultTableModel defaultTableModel = new DefaultTableModel();
    private final TenderController tenderController=new TenderController();

    /**
     * Create the panel.
     */
    public AgenceTenderInfoJPanel() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("投标信息查看");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 40));
        lblNewLabel.setBounds(162, 0, 531, 84);
        add(lblNewLabel);
        
        table = new JTable(defaultTableModel);
        table.setBounds(32, 181, 782, 332);
        table.setRowSelectionAllowed(true);

        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(32, 181, 782, 332);
        add(scollPane);
        
        JButton btnNewButton = new JButton("查看已中标信息");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                defaultTableModel.setRowCount(0);
                try {
                    showMessage(0);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(60, 141, 203, 27);
        add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("查看所有竞标信息");
        btnNewButton_1.addActionListener(e -> {

                defaultTableModel.setRowCount(0);
                try {
                    showMessage(1);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

        });
        btnNewButton_1.setBounds(555, 141, 203, 27);
        add(btnNewButton_1);
        
        JButton button = new JButton("结束订单");
        button.addActionListener(arg0 -> {
            int num = table.getSelectedRow();
            String iniCode=defaultTableModel.getValueAt(num,1).toString();
            String iniPeople=defaultTableModel.getValueAt(num,3).toString();
            try {
                if(tenderController.modify(iniCode,iniPeople,"订单结束")){
                    JOptionPane.showMessageDialog(null,"订单已结束");
                    defaultTableModel.setRowCount(0);
                    showMessage(1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        button.setBounds(345, 141, 113, 27);
        add(button);
        
        JButton btnNewButton_2 = new JButton("确认选择该工厂的投标");
        btnNewButton_2.addActionListener(arg0 -> {
            int find = JOptionPane.showConfirmDialog(null, "是否确定该工厂中标", "温馨提示", JOptionPane.YES_NO_OPTION);
            if(find==0){
                int num = table.getSelectedRow();
                String iniCode=defaultTableModel.getValueAt(num,1).toString();
                String iniPeople=defaultTableModel.getValueAt(num,3).toString();
                try {
                    if (defaultTableModel.getValueAt(num,8).toString().equals("竞标发布")){
                        if(tenderController.modify(iniCode,iniPeople,"中标确定")){
                            JOptionPane.showMessageDialog(null,"中标已确定");
                            defaultTableModel.setRowCount(0);
                            showMessage(1);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        btnNewButton_2.setBounds(248, 91, 328, 27);
        add(btnNewButton_2);
        
        defaultTableModel.addColumn("序号");
        defaultTableModel.addColumn("订单编号");
        defaultTableModel.addColumn("订单名称");
        defaultTableModel.addColumn("投标人");
        defaultTableModel.addColumn("投标人工厂");
        defaultTableModel.addColumn("投标时间");
        defaultTableModel.addColumn("单价");
        defaultTableModel.addColumn("承载数量");
        defaultTableModel.addColumn("竞标状态");
        
        

    }
    /**
     * @throws IOException:
     * @description 展示产品信息
     */
    public void showMessage(int number) throws IOException {

        List<Object> objects = tenderController.getList();
        int count=1;
        if (number==1) {
            for (int i = 0; i < objects.size(); i++) {
                addTableRow((Tender) objects.get(i), i + 1);
            }
        }else {
            for (int i = 0; i < objects.size(); i++) {
                 
                Tender tender=(Tender) objects.get(i);
                if (tender.getTenderState().equals("中标确定")) {
                    addTableRow(tender, count++);
                }
                
            }
        }
        
    }


    /**
     * @param num:序号
     * @description 添加一行表格信息
     */
    public void addTableRow(Tender tender, int num) {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        rowData.add(num);
        rowData.add(tender.getOrderNumber());
        rowData.add(tender.getOrderName());
        rowData.add(tender.getTenderPeo());
        rowData.add(tender.getTenderFactor());
        rowData.add(tender.getTenderDate());
        rowData.add(tender.getPrice());
        rowData.add(tender.getTenderNumber());
        rowData.add(tender.getTenderState());
        // 添加一行
        defaultTableModel.addRow(rowData); // 添加一行
    }
}
