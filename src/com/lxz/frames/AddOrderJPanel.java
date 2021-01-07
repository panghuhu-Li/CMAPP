package com.lxz.frames;

import com.lxz.controllers.ProductController;
import com.lxz.entity.ProductType;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.io.IOException;
import java.util.List;

public class AddOrderJPanel extends JPanel {
    private JTextField orderName;
    private JTextField orderNumber;
    private JTextField orderDate;
    private JTextField orderDeadline;
    private JTextField receivePeople;
    private JTextField linkWay;
    private JTextField receiveSite;
    private ProductController productController = new ProductController();

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
        
        orderNumber = new JTextField();
        orderNumber.setBounds(159, 123, 176, 24);
        add(orderNumber);
        orderNumber.setColumns(10);
        
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
            for (int i = 0; i < objects.size(); i++) {
                ProductType productType = (ProductType) objects.get(i);
                comboBoxOfProductType.addItem(productType.getTypeName());
            }
        }
        comboBoxOfProductType.setBounds(159, 547, 176, 24);
        add(comboBoxOfProductType);
        
        JLabel label = new JLabel("订单类型");
        label.setBounds(32, 550, 72, 18);
        add(label);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(159, 484, 176, 24);
        add(comboBox);

    }
}
