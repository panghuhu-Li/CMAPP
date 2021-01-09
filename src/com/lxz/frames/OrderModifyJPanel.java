package com.lxz.frames;

import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lxz.controllers.OrderController;
import com.lxz.controllers.ProductController;
import com.lxz.entity.Order;
import com.lxz.entity.ProductType;

public class OrderModifyJPanel extends JPanel {
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
    public OrderModifyJPanel(String iniCode, String iniName, String iniNumber, String iniDate, String iniDeadline, String iniPeople, String iniLinlWay, String iniPlace) {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("订单名称");
        lblNewLabel.setBounds(32, 63, 72, 18);
        add(lblNewLabel);

        orderName = new JTextField();
        orderName.setBounds(159, 60, 176, 24);
        orderName.setText(iniName);
        add(orderName);
        orderName.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("订购数量");
        lblNewLabel_1.setBounds(32, 126, 72, 18);
        add(lblNewLabel_1);

        orderAmount = new JTextField();
        orderAmount.setBounds(159, 123, 176, 24);
        orderAmount.setText(iniNumber);
        add(orderAmount);
        orderAmount.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("交付日期");
        lblNewLabel_2.setBounds(32, 185, 72, 18);
        add(lblNewLabel_2);

        orderDate = new JTextField();
        orderDate.setBounds(159, 182, 176, 24);
        orderDate.setText(iniDate);
        add(orderDate);
        orderDate.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("投标截止日期");
        lblNewLabel_3.setBounds(32, 251, 113, 18);
        add(lblNewLabel_3);

        orderDeadline = new JTextField();
        orderDeadline.setBounds(159, 248, 176, 24);
        orderDeadline.setText(iniDeadline);
        add(orderDeadline);
        orderDeadline.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("收货人");
        lblNewLabel_4.setBounds(32, 315, 72, 18);
        add(lblNewLabel_4);

        receivePeople = new JTextField();
        receivePeople.setBounds(159, 312, 176, 24);
        receivePeople.setText(iniPeople);
        add(receivePeople);
        receivePeople.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("收货人联系方式");
        lblNewLabel_5.setBounds(32, 373, 113, 18);
        add(lblNewLabel_5);

        linkWay = new JTextField();
        linkWay.setBounds(159, 370, 176, 24);
        linkWay.setText(iniLinlWay);
        add(linkWay);
        linkWay.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("收货地点");
        lblNewLabel_6.setBounds(32, 433, 72, 18);
        add(lblNewLabel_6);

        receiveSite = new JTextField();
        receiveSite.setBounds(159, 430, 176, 24);
        receiveSite.setText(iniPlace);
        add(receiveSite);
        receiveSite.setColumns(10);


        JButton button = new JButton("修改");
        button.addActionListener(arg0 -> {
            String name = orderName.getText();
            String amount = orderAmount.getText();
            String date = orderDate.getText();
            String deadline = orderDeadline.getText();
            String people = receivePeople.getText();
            String contactWay = linkWay.getText();
            String place = receiveSite.getText();
            try {
                if (orderController.modify(iniCode,name,amount,date,deadline,people,contactWay,place)) {
                    JOptionPane.showMessageDialog(null, "修改成功");
                    AddOrderJFrame addOrderJFrame = AddOrderJFrame.creatInstance();
                    addOrderJFrame.setVisible(false);
                    addOrderJFrame.setBounds(100, 100, 400, 500);
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
