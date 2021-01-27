package com.lxz.frames;

import com.lxz.controllers.OrderController;
import com.lxz.controllers.ProductController;
import com.lxz.entity.Order;
import com.lxz.entity.ProductType;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddOrderJPanel extends JPanel {
    private final JTextField orderName;
    private final JTextField orderAmount;
    private final JTextField orderDate;
    private final JTextField orderDeadline;
    private final JTextField receivePeople;
    private final JTextField linkWay;
    private final JTextField receiveSite;
    private ProductController productController = new ProductController();
    private OrderController orderController = new OrderController();
    private String type;
    private String state;

    /**
     * Create the panel.
     */
    public AddOrderJPanel() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("订单名称");
        lblNewLabel.setBounds(32, 63, 72, 18);
        add(lblNewLabel);

        orderName = new JTextField();
        orderName.setBounds(159, 60, 176, 24);
        add(orderName);
        orderName.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("订购数量");
        lblNewLabel_1.setBounds(32, 126, 72, 18);
        add(lblNewLabel_1);

        orderAmount = new JTextField();
        orderAmount.setBounds(159, 123, 176, 24);
        add(orderAmount);
        orderAmount.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("交付日期");
        lblNewLabel_2.setBounds(32, 185, 72, 18);
        add(lblNewLabel_2);

        orderDate = new JTextField();
        orderDate.setBounds(159, 182, 176, 24);
        add(orderDate);
        orderDate.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("投标截止日期");
        lblNewLabel_3.setBounds(32, 251, 113, 18);
        add(lblNewLabel_3);

        orderDeadline = new JTextField();
        orderDeadline.setBounds(159, 248, 176, 24);
        add(orderDeadline);
        orderDeadline.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("收货人");
        lblNewLabel_4.setBounds(32, 315, 72, 18);
        add(lblNewLabel_4);

        receivePeople = new JTextField();
        receivePeople.setBounds(159, 312, 176, 24);
        add(receivePeople);
        receivePeople.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("收货人联系方式");
        lblNewLabel_5.setBounds(32, 373, 113, 18);
        add(lblNewLabel_5);

        linkWay = new JTextField();
        linkWay.setBounds(159, 370, 176, 24);
        add(linkWay);
        linkWay.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("收货地点");
        lblNewLabel_6.setBounds(32, 433, 72, 18);
        add(lblNewLabel_6);

        receiveSite = new JTextField();
        receiveSite.setBounds(159, 430, 176, 24);
        add(receiveSite);
        receiveSite.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("订单状态");
        lblNewLabel_7.setBounds(32, 487, 72, 18);
        add(lblNewLabel_7);


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
            for (Object object : objects) {
                ProductType productType = (ProductType) object;
                comboBoxOfProductType.addItem(productType.getTypeName());
            }
        }
        comboBoxOfProductType.addActionListener(e -> {
            type = comboBoxOfProductType.getSelectedItem().toString();
        });
        comboBoxOfProductType.setBounds(159, 547, 176, 24);
        add(comboBoxOfProductType);

        JLabel label = new JLabel("订单类型");
        label.setBounds(32, 550, 72, 18);
        add(label);

        JComboBox<String> comboBoxOfProductState = new JComboBox<>();
        comboBoxOfProductType.addItem("《---请选择---》");
        comboBoxOfProductState.addItem("竞标发布");
        comboBoxOfProductState.addItem("中标确定");
        comboBoxOfProductState.addItem("订单生产");
        comboBoxOfProductState.addItem("工厂发货");
        comboBoxOfProductState.addItem("订单结束");
        comboBoxOfProductState.addItem("无竞标");
        comboBoxOfProductState.addActionListener(e -> {
            state = comboBoxOfProductState.getSelectedItem().toString();
        });
        comboBoxOfProductState.setBounds(159, 484, 176, 24);
        add(comboBoxOfProductState);

        JButton button = new JButton("新增");
        button.addActionListener(arg0 -> {
            String number = "2021" + (int) (Math.random() * 1000000);
            String name = orderName.getText();
            String amount = orderAmount.getText();
            String date = orderDate.getText();
            String deadline = orderDeadline.getText();
            String people = receivePeople.getText();
            String contactWay = linkWay.getText();
            String place = receiveSite.getText();
            Order order = new Order(number, name, amount, date, deadline, people, contactWay, place, state, type);
            try {
                if (orderController.add(order)) {
                    JOptionPane.showMessageDialog(null, "添加成功");
                    AddOrderJFrame addOrderJFrame = AddOrderJFrame.creatInstance();
                    addOrderJFrame.setVisible(false);
                    AgencyJFrame agencyJFrame = AgencyJFrame.creatInstance();
                    agencyJFrame.changeJFrame("订单", new OrderJPanel());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button.setBounds(139, 20, 113, 27);
        add(button);

    }
}
