package com.lxz.frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.EquipmentController;
import com.lxz.entity.EquipmentInfo;

import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * @program: CMAPP
 * @description 租用功能实现面板
 * @author: 李星泽
 * @create: 2020-07-18 09:46
 **/
public class RentJPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private EquipmentController equipmentController = new EquipmentController();

    /**
     * 创建租用功能实现面板
     */
    public RentJPanel(String aff) {
        setLayout(null);

        JButton btnNewButton = new JButton("租用");
        btnNewButton.addActionListener(arg0 -> {
            int find = JOptionPane.showConfirmDialog(null, "是否要租用该设备", "温馨提示", JOptionPane.YES_NO_OPTION);
            if (find == 0) {
                int num = table.getSelectedRow();
                if (num < 0 || num > table.getRowCount() - 1) {
                    JOptionPane.showMessageDialog(null, "请选择你租用的设备");
                    return;
                }
                //只能租用未租用并且属于超级管理员的设备
                if (defaultTableModel.getValueAt(num, 7).toString().equals("未租用")
                        && defaultTableModel.getValueAt(num, 8).toString().equals("超级管理员")) {

                    try {
                        boolean success = equipmentController.rent(defaultTableModel.getValueAt(num, 1).toString(),
                                aff);
                        if (success) {
                            JOptionPane.showMessageDialog(null, "租用成功");
                            defaultTableModel.setRowCount(0);
                            showMessage();
                        } else {
                            JOptionPane.showMessageDialog(null, "租用失败");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "该设备无法被租用");
                }
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 30));
        btnNewButton.setBounds(34, 69, 174, 51);
        add(btnNewButton);

        table = new JTable(defaultTableModel);
        table.setBounds(34, 174, 787, 345);
        table.setRowHeight(25);

        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(34, 174, 787, 345);
        add(scollPane);

        defaultTableModel.addColumn("序号");
        defaultTableModel.addColumn("设备编号");
        defaultTableModel.addColumn("设备名称");
        defaultTableModel.addColumn("设备类型");
        defaultTableModel.addColumn("设备规格");
        defaultTableModel.addColumn("设备描述");
        defaultTableModel.addColumn("设备状态");
        defaultTableModel.addColumn("租用状态");
        defaultTableModel.addColumn("所属工厂");

        try {
            showMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 展示管理员信息
    public void showMessage() throws IOException {
        int num = 0;
        // 判断展示什么信息
        List<Object> objects = equipmentController.getEquipmentInfo();
        for (int i = 0; i < objects.size(); i++) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            //只展示属于超级管理员并且未租用的设备
            if (equipmentInfo.getAffiliation().equals("超级管理员")
                    && equipmentInfo.isRented().equals("未租用")) {
                num++;
                addTableRow((EquipmentInfo) objects.get(i), num);
            }

        }
    }

    // 添加一行表格信息
    public void addTableRow(EquipmentInfo equipmentInfo, int num) {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        // 序号
        rowData.add(num);
        // 设备编号
        rowData.add(equipmentInfo.getEquipmentNumber());
        // 设备名称
        rowData.add(equipmentInfo.getEquipmentName());
        // 设备类型
        rowData.add(equipmentInfo.getEquipmentType());
        // 设备规格
        rowData.add(equipmentInfo.getEquipmentSpec());
        // 设备描述
        rowData.add(equipmentInfo.getEquipmentDesc());
        // 设备状态
        rowData.add(equipmentInfo.getEquipmentState());
        // 租用状态
        rowData.add(equipmentInfo.isRented());
        // 所属工厂
        rowData.add(equipmentInfo.getAffiliation());
        // 添加一行
        defaultTableModel.addRow(rowData); // 添加一行
    }
}
