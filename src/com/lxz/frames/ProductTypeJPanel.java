package com.lxz.frames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.lxz.entity.Administrator;
import com.lxz.entity.ProductType;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ProductTypeJPanel extends JPanel {
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private JTextField textField;
    private JTable table;

    /**
     * Create the panel.
     */
    public ProductTypeJPanel() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("产品类型");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
        lblNewLabel.setBounds(32, 115, 116, 31);
        add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(303, 120, 166, 24);
        add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("新建");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton.setBounds(679, 182, 113, 27);
        add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("检索");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_1.setBounds(679, 280, 113, 27);
        add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("修改");
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnNewButton_2.setBounds(679, 384, 113, 27);
        add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("删除");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton_3.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_3.setBounds(679, 501, 113, 27);
        add(btnNewButton_3);
        
        table = new JTable();
        table.setBounds(77, 182, 535, 349);
        defaultTableModel.addColumn("序号");
        defaultTableModel.addColumn("类别名称");
        
        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(77, 182, 535, 349);
        add(scollPane);
        
        JLabel lblNewLabel_1 = new JLabel("欢迎进入产品类型管理界面");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 30));
        lblNewLabel_1.setBounds(57, 27, 624, 62);
        add(lblNewLabel_1);
        
        try {
            showMessage();
        } catch (IOException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }
    }
    
    // 展示管理员信息
    public void showMessage() throws IOException {

//        List<Object> objects = adminidtratorcontrollers.getAdministrator();
//        for (int i = 0; i < objects.size(); i++) {
//            addTableRow((Administrator) objects.get(i));
//        }
    }

    // 添加一行表格信息
    public void addTableRow(ProductType productType, int number) {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        // 登录账号
        rowData.add(number);
        // 姓名
        rowData.add(productType.getTypeName());
        // 添加一行
        defaultTableModel.addRow(rowData); // 添加一行

    }
}
