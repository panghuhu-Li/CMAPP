package com.lxz.frames;

import javax.swing.*;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.OrderController;
import com.lxz.controllers.ProductController;
import com.lxz.entity.Order;
import com.lxz.entity.ProductInfo;
import com.lxz.entity.ProductType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderJPanel extends JPanel {
    private JTextField orderName;
    private JTextField orderNumber;
    private ProductController productController = new ProductController();
    private JTable table;
    private final DefaultTableModel defaultTableModel = new DefaultTableModel();
    private OrderController orderController = new OrderController();

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

        JComboBox<String> comboBoxOfProductType = new JComboBox<>();
        if (objects == null || objects.size() == 0) {
            comboBoxOfProductType.addItem("《无》");
        } else {
            for (Object object : objects) {
                ProductType productType = (ProductType) object;
                comboBoxOfProductType.addItem(productType.getTypeName());
            }
        }
        comboBoxOfProductType.setBounds(535, 107, 186, 24);
        add(comboBoxOfProductType);

        JLabel lblNewLabel_1 = new JLabel("订单产品");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
        lblNewLabel_1.setBounds(429, 107, 72, 18);
        add(lblNewLabel_1);

        JComboBox<String> comboBoxOfProductState = new JComboBox<>();
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
        button.addActionListener(arg0 -> {
            AddOrderJFrame addOrderJFrame = AddOrderJFrame.creatInstance();
            addOrderJFrame.changeJFrame("添加订单", new AddOrderJPanel());
        });
        button.setBounds(57, 180, 113, 27);
        add(button);

        JButton btnNewButton = new JButton("删除");
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 15));
        btnNewButton.addActionListener(e -> {
            int num = table.getSelectedRow();
            if (num < 0 || num > table.getRowCount() - 1) {
                JOptionPane.showMessageDialog(null, "请选择你要删除的行");
            }
            String orderNumber = defaultTableModel.getValueAt(num, 1).toString();
            try {
                if (orderController.delete(orderNumber)) {
                    JOptionPane.showMessageDialog(null, "删除成功");
                    defaultTableModel.setRowCount(0);
                    showMessage();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        btnNewButton.setBounds(237, 180, 113, 27);
        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("更改");
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 15));
        btnNewButton_1.addActionListener(e -> {
            int num = table.getSelectedRow();
            String orderNumber = defaultTableModel.getValueAt(num, 1).toString();
            String iniName = defaultTableModel.getValueAt(num, 2).toString();
            String iniNumber = defaultTableModel.getValueAt(num, 3).toString();
            String iniDate = defaultTableModel.getValueAt(num, 4).toString();
            String iniDeadline = defaultTableModel.getValueAt(num, 5).toString();
            String iniPeople = defaultTableModel.getValueAt(num, 6).toString();
            String iniLinkWay = defaultTableModel.getValueAt(num, 7).toString();
            String iniPlace = defaultTableModel.getValueAt(num, 8).toString();
            AddOrderJFrame addOrderJFrame=AddOrderJFrame.creatInstance();
            addOrderJFrame.changeJFrame("修改",new OrderModifyJPanel(orderNumber,iniName,iniNumber,iniDate,iniDeadline,iniPeople,iniLinkWay,iniPlace));

//            try {
//                if (orderController.modify(orderNumber, orderName.getText())) {
//                    JOptionPane.showMessageDialog(null, "修改成功");
//                    defaultTableModel.setRowCount(0);
//                    showMessage();
//                }
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
        });
        btnNewButton_1.setBounds(419, 180, 113, 27);
        add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("查找");
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 15));
        btnNewButton_2.addActionListener(e -> {
            int num = table.getSelectedRow();
            String orderNumber = defaultTableModel.getValueAt(num, 1).toString();
            try {
                Order order = (Order) orderController.search(orderNumber);
                // 在表格中显示信息
                defaultTableModel.setRowCount(0);
                // 显示查找的设备类型
                addTableRow(order, 1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
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
        defaultTableModel.addColumn("订单名称");
        defaultTableModel.addColumn("订购数量");
        defaultTableModel.addColumn("交付日期");
        defaultTableModel.addColumn("投标截止日期");
        defaultTableModel.addColumn("收货人");
        defaultTableModel.addColumn("收货人联系方式");
        defaultTableModel.addColumn("收货地点");

        try {
            showMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @throws IOException:
     * @description 展示产品信息
     */
    public void showMessage() throws IOException {

        List<Object> objects = orderController.getList();
        for (int i = 0; i < objects.size(); i++) {
            addTableRow((Order) objects.get(i), i + 1);
        }
    }


    /**
     * @param order:
     * @param num:序号
     * @description 添加一行表格信息
     */
    public void addTableRow(Order order, int num) {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        rowData.add(num);
        rowData.add(order.getOrderNumber());
        rowData.add(order.getProductName());
        rowData.add(order.getOrderAmount());
        rowData.add(order.getDayOfDeliver());
        rowData.add(order.getDayOfDecline());
        rowData.add(order.getConsignee());
        rowData.add(order.getContactWay());
        rowData.add(order.getPlaceOfReceive());
        // 添加一行
        defaultTableModel.addRow(rowData); // 添加一行
    }
}
