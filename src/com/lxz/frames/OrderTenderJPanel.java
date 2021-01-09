package com.lxz.frames;

import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.OrderController;
import com.lxz.entity.Order;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderTenderJPanel extends JPanel {
    private JTable table;
    private final DefaultTableModel defaultTableModel = new DefaultTableModel();
    private OrderController orderController = new OrderController();

    /**
     * Create the panel.
     */
    public OrderTenderJPanel() {
        setLayout(null);

        table = new JTable(defaultTableModel);
        table.setBounds(32, 181, 782, 332);
        table.setRowSelectionAllowed(true);

        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(32, 181, 782, 332);
        add(scollPane);

        JButton btnNewButton = new JButton("投标");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int num = table.getSelectedRow();
                if (num < 0 || num > table.getRowCount() - 1) {
                    JOptionPane.showMessageDialog(null, "请选择你要投标的订单");
                    return;
                }
                String orderNumber = defaultTableModel.getValueAt(num, 1).toString();
                String orderName = defaultTableModel.getValueAt(num, 2).toString();
                TenderJFrame tenderJFrame = TenderJFrame.creatInstance();
                tenderJFrame.changeJFrame("投标", new TenderJPanel(orderNumber, orderName));
            }
        });
        btnNewButton.setBounds(61, 54, 138, 53);
        add(btnNewButton);

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
