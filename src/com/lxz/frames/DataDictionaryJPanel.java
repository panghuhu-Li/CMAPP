package com.lxz.frames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.DictionaryDataController;
import com.lxz.entity.Dictionary;
import com.lxz.entity.EquipmentInfo;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DataDictionaryJPanel extends JPanel {
    private JTextField dictionaryName;
    private JTextField dictionaryType;
    private JTable table;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private DictionaryDataController dictionaryDataController = new DictionaryDataController();

    /**
     * Create the panel.
     */
    public DataDictionaryJPanel() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("字典项名称");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 17));
        lblNewLabel.setBounds(14, 86, 141, 31);
        add(lblNewLabel);

        dictionaryName = new JTextField();
        dictionaryName.setBounds(169, 90, 218, 24);
        add(dictionaryName);
        dictionaryName.setColumns(10);

        JLabel label = new JLabel("字典类型码");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("宋体", Font.BOLD, 17));
        label.setBounds(401, 92, 127, 18);
        add(label);

        dictionaryType = new JTextField();
        dictionaryType.setBounds(556, 90, 259, 24);
        add(dictionaryType);
        dictionaryType.setColumns(10);

        table = new JTable(defaultTableModel);
        table.setBounds(49, 211, 754, 291);


        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(49, 211, 754, 291);
        add(scollPane);

        JButton button = new JButton("查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                SuperJFrame superJFrame = SuperJFrame.creatInstance();

                if (dictionaryName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入你要查询的信息");
                    return;
                }

                if (dictionaryName.getText().equals("设备状态") || dictionaryType.getText().equals("equiomentState")) {
                    superJFrame.changeJFrame("设备状态", new EquipmentJPanel());
                } else {
                    JOptionPane.showMessageDialog(null, "没有该数据字典类型");
                }

            }
        });
        button.setFont(new Font("宋体", Font.BOLD, 19));
        button.setBounds(401, 154, 113, 27);
        add(button);

        JButton button_1 = new JButton("添加");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String name = dictionaryName.getText();
                String typeCode = dictionaryType.getText();
                String dictionaryNumber = "2021" + (int)(Math.random()*10000);
                Dictionary dictionary=new Dictionary(dictionaryNumber,typeCode,name,1);

                try {
                    dictionaryDataController.add(dictionary);
                    dictionaryName.setText("");
                    dictionaryName.requestFocus();
                    dictionaryType.setText("");
                    dictionaryType.requestFocus();
                    //清空原表格中的信息
                    defaultTableModel.setRowCount(0);
                    //展示最新信息
                    showMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button_1.setFont(new Font("宋体", Font.BOLD, 19));
        button_1.setBounds(255, 154, 113, 27);
        add(button_1);

        JButton button_2 = new JButton("删除");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int num = table.getSelectedRow();
                System.out.println(num);
                System.out.println("1  "+defaultTableModel.getValueAt(num, 2).toString());
                try {
                    dictionaryDataController.delete(defaultTableModel.getValueAt(num, 1).toString());
                    //清空原表格中的信息
                    defaultTableModel.setRowCount(0);
                    //展示最新信息
                    showMessage();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        button_2.setFont(new Font("宋体", Font.BOLD, 19));
        button_2.setBounds(557, 156, 113, 27);
        add(button_2);

        JButton button_3 = new JButton("修改");
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button_3.setFont(new Font("宋体", Font.BOLD, 19));
        button_3.setBounds(702, 156, 113, 27);
        add(button_3);

        defaultTableModel.addColumn("序号");
        defaultTableModel.addColumn("编码");
        defaultTableModel.addColumn("字典类型码");
        defaultTableModel.addColumn("类型名称");
        defaultTableModel.addColumn("子项数量");

        try {
            showMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 展示管理员信息
    public void showMessage() throws IOException {

        List<Object> objects = dictionaryDataController.getDictionaryData();
        for (int i = 0; i < objects.size(); i++) {
            addTableRow((Dictionary) objects.get(i), i + 1);
        }
    }

    // 添加一行表格信息
    public void addTableRow(Dictionary dictionary, int num) {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        // 序号
        rowData.add(num);
        // 设备编号
        rowData.add(dictionary.getDictionaryNumber());
        // 设备名称
        rowData.add(dictionary.getTypeCode());
        // 设备类型
        rowData.add(dictionary.getName());
        // 设备规格
        rowData.add(dictionary.getNumber());

        defaultTableModel.addRow(rowData); // 添加一行
    }
}
