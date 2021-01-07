package com.lxz.frames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.ProductController;
import com.lxz.entity.ProductInfo;
import com.lxz.entity.ProductType;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class OrderJPanel extends JPanel {
    private JTextField orderName;
    private JTextField orderNumber;
    private ProductController productController = new ProductController();
    private JTable table;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    /**
     * Create the panel.
     */
    public OrderJPanel() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("订单名称");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel.setBounds(57, 47, 69, 18);
        add(lblNewLabel);
        
        orderName = new JTextField();
        orderName.setBounds(149, 44, 153, 24);
        add(orderName);
        orderName.setColumns(10);
        
        JLabel label = new JLabel("订单编号");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(429, 47, 72, 18);
        add(label);
        
        orderNumber = new JTextField();
        orderNumber.setBounds(535, 44, 186, 24);
        add(orderNumber);
        orderNumber.setColumns(10);
        
        JLabel label_1 = new JLabel("订单状态");
        label_1.setFont(new Font("宋体", Font.BOLD, 15));
        label_1.setBounds(57, 110, 72, 18);
        add(label_1);
        
        List<Object> objects = null;
        try {
            objects = productController.getProductType();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JComboBox<String> comboBoxOfProductType = new JComboBox<String>();
        if (objects == null || objects.size() == 0) {
            comboBoxOfProductType.addItem("《---请选择---》");
        } else {
            for (int i = 0; i < objects.size(); i++) {
                ProductType productType = (ProductType) objects.get(i);
                comboBoxOfProductType.addItem(productType.getTypeName());
            }
        }
        comboBoxOfProductType.setBounds(535, 107, 186, 24);
        add(comboBoxOfProductType);
        
        JLabel lblNewLabel_1 = new JLabel("订单产品");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_1.setBounds(429, 107, 72, 18);
        add(lblNewLabel_1);
        
        JComboBox<String> comboBoxOfProductState = new JComboBox<String>();
        comboBoxOfProductState.addItem("竞标发布");
        comboBoxOfProductState.addItem("中标确定");
        comboBoxOfProductState.addItem("订单生产");
        comboBoxOfProductState.addItem("工厂发货");
        comboBoxOfProductState.addItem("订单结束");
        comboBoxOfProductState.addItem("无竞标");
        comboBoxOfProductState.setBounds(149, 107, 153, 24);
        add(comboBoxOfProductState);
        
        JButton button = new JButton("新增");
        button.setFont(new Font("宋体", Font.BOLD, 15));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AddOrderJFrame addOrderJFrame=AddOrderJFrame.creatInstance();
                addOrderJFrame.changeJFrame("添加订单", new AddOrderJPanel());
            }
        });
        button.setBounds(57, 180, 113, 27);
        add(button);
        
        JButton btnNewButton = new JButton("删除");
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 15));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBounds(237, 180, 113, 27);
        add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("更改");
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 15));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton_1.setBounds(419, 180, 113, 27);
        add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("查找");
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 15));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton_2.setBounds(592, 180, 113, 27);
        add(btnNewButton_2);
        
        table = new JTable(defaultTableModel);
        table.setBounds(33, 241, 721, 273);
        table.setRowSelectionAllowed(true);
        
        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(33, 241, 721, 273);
        add(scollPane);
        
        defaultTableModel.addColumn("序号");
        defaultTableModel.addColumn("订单编号");
        defaultTableModel.addColumn("订购数量");
        defaultTableModel.addColumn("交付日期");
        defaultTableModel.addColumn("投标截止日期");
        defaultTableModel.addColumn("收货人");
        defaultTableModel.addColumn("收货人联系方式");
        defaultTableModel.addColumn("收货地点");
        
    }
    
    /**
     * @throws IOException:
     * @description 展示产品信息
     */
    public void showMessage() throws IOException {

//        List<Object> objects = productController.getProductInfo();
//        for (int i = 0; i < objects.size(); i++) {
//            addTableRow((ProductInfo) objects.get(i), i + 1);
//        }
    }


    /**
     * @param productInfo:
     * @param num:序号
     * @description 添加一行表格信息
     */
    public void addTableRow(ProductInfo productInfo, int num) {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        // 序号
        rowData.add(num);
        // 产品编号
        rowData.add(productInfo.getProductNumber());
        // 产品名称
        rowData.add(productInfo.getProductName());
        // 产品类别
        rowData.add(productInfo.getProductType());
        // 产品规格
        rowData.add(productInfo.getProductSpec());
        // 产品描述
        rowData.add(productInfo.getProductDesc());
        // 添加一行
        defaultTableModel.addRow(rowData); // 添加一行
    }
}
