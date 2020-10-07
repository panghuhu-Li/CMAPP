package com.lxz.frames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.EquipmentController;
import com.lxz.entity.EquipmentType;

/**
 * @program: CMAPP
 * @description 设备类型功能实现面板
 * @author: 李星泽
 * @create: 2020-07-16 17:56
 **/
public class EquipmentTypeJPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JTextField equipmentType;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private EquipmentController equipmentController = new EquipmentController();

    /**
     * 创建设备类型的面板
     */
    public EquipmentTypeJPanel() {
        setLayout(null);

        JButton btnNewButton = new JButton("新建");
        // 新建按钮事件监听
        btnNewButton.addActionListener(e -> {
            // 判断要添加的设备类型输入框是否为空
            if (equipmentType.getText() == null || equipmentType.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入你要添加的设备类型");
                return;
            }
            // 创建设备类型对象进行添加
            EquipmentType type = new EquipmentType();
            type.setTypeName(equipmentType.getText());

            try {
                // 添加设备类型对象
                equipmentController.addType(type);
                equipmentType.setText("");
                equipmentType.requestFocus();
                // 清空表格信息
                defaultTableModel.setRowCount(0);
                // 显示最新的表格信息
                showMessage();
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }

        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton.setBounds(631, 144, 121, 36);
        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("检索");
        // 检索按钮事件建监听
        btnNewButton_1.addActionListener(e -> {
            // 要搜索的设备类型不能为空
            if (equipmentType.getText() == null || equipmentType.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入你要搜索的设备类型");
                return;
            }
            try {
                // 查找设备类型
                EquipmentType eypEquipmentType = (EquipmentType) equipmentController
                        .searchEquipmentType(equipmentType.getText());
                if (eypEquipmentType == null) {
                    JOptionPane.showMessageDialog(null, "不存在该产品类型");
                } else {
                    // 在表格中显示信息
                    defaultTableModel.setRowCount(0);
                    // 显示查找的设备类型
                    addTableRow(eypEquipmentType, 1);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_1.setBounds(631, 248, 121, 36);
        add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("删除");
        // 删除事件按钮监听
        btnNewButton_2.addActionListener(e -> {
            // 提示是否要删除信息 是find==0
            int find = JOptionPane.showConfirmDialog(null, "是否确定要删除该产品类型", "温馨提示", JOptionPane.YES_NO_OPTION);
            if (find == 0) {
                // 获取要删除的行
                int num = table.getSelectedRow();
                // 判断是否选中要删除的行
                if (num < 0 || num > table.getRowCount() - 1) {
                    JOptionPane.showMessageDialog(null, "请选择你要删除的产品类型");
                    return;
                }
                try {
                    // 通过设备类型名称进行删除
                    boolean success = equipmentController.deleteType(defaultTableModel.getValueAt(num, 1).toString());
                    if (success) {
                        // 删除所有该类型的设备
                        equipmentController.delete(defaultTableModel.getValueAt(num, 1).toString());
                        // 删除选中的行
                        defaultTableModel.removeRow(num);
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败可能不存在该设备");
                    }
                } catch (IOException e1) {
                    // TODO 自动生成的 catch 块
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_2.setBounds(631, 353, 121, 36);
        add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("修改");
        // 修改按钮事件监听
        btnNewButton_3.addActionListener(e -> {
            // 提示是都要进行设备类型的修改
            int find = JOptionPane.showConfirmDialog(null, "是否确定要修改该产品类型", "温馨提示", JOptionPane.YES_NO_OPTION);
            if (find == 0) {
                // 获得要修改的行
                int num = table.getSelectedRow();
                // 判断是否选中修改的行
                if (num < 0 || num > table.getRowCount() - 1) {
                    JOptionPane.showMessageDialog(null, "请选择你要修改的产品类型");
                    return;
                }
                if (equipmentType.getText() == null || equipmentType.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入要修改的产品类型");
                    return;
                }

                try {
                    // 设备类型修改
                    boolean success = equipmentController.modifyType(defaultTableModel.getValueAt(num, 1).toString(),
                            equipmentType.getText());
                    if (success) {
                        JOptionPane.showMessageDialog(null, "修改成功");
                        // 清空表格中的内容
                        defaultTableModel.setRowCount(0);
                        // 展示最新的表格中的内容
                        showMessage();
                    }
                } catch (IOException e1) {
                    // TODO 自动生成的 catch 块
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton_3.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_3.setBounds(631, 449, 121, 36);
        add(btnNewButton_3);

        equipmentType = new JTextField();
        equipmentType.setBounds(338, 45, 212, 49);
        add(equipmentType);
        equipmentType.setColumns(10);

        JLabel lblNewLabel = new JLabel("类型名称");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 25));
        lblNewLabel.setBounds(151, 45, 111, 49);
        add(lblNewLabel);

        table = new JTable(defaultTableModel);
        table.setBounds(28, 144, 522, 358);
        table.setRowHeight(25);

        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(28, 144, 522, 358);
        add(scollPane);

        // 添加表格中的列信息
        defaultTableModel.addColumn("序号");
        defaultTableModel.addColumn("类型名称");

        // 展示表格信息
        try {
            showMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 展示管理员信息
    public void showMessage() throws IOException {

        List<Object> objects = equipmentController.getEquipmentType();
        for (int i = 0; i < objects.size(); i++) {
            addTableRow((EquipmentType) objects.get(i), i + 1);
        }
    }

    // 添加一行表格信息
    public void addTableRow(EquipmentType equipmentType, int num) {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        // 序号
        rowData.add(num);
        // 设备编号
        rowData.add(equipmentType.getTypeName());
        // 添加一行
        defaultTableModel.addRow(rowData); // 添加一行
    }
}
