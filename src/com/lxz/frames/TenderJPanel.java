package com.lxz.frames;

import com.lxz.controllers.TenderController;
import com.lxz.entity.Tender;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TenderJPanel extends JPanel {
    private JTextField tenderName;
    private JTextField tenderFac;
    private JTextField tenderPrice;
    private JTextField tenderNumber;
    private TenderController tenderController=new TenderController();

    /**
     * Create the panel.
     */
    public TenderJPanel(String orderNumber,String orderName) {
        setLayout(null);
        
        JLabel label = new JLabel("投标单价");
        label.setBounds(29, 223, 72, 18);
        add(label);
        
        JLabel label_1 = new JLabel("投标人");
        label_1.setBounds(29, 77, 72, 18);
        add(label_1);
        
        tenderName = new JTextField();
        tenderName.setBounds(143, 74, 143, 24);
        add(tenderName);
        tenderName.setColumns(10);
        
        JLabel tenderFactory = new JLabel("投标人工厂");
        tenderFactory.setBounds(29, 153, 88, 18);
        add(tenderFactory);
        
        tenderFac = new JTextField();
        tenderFac.setBounds(143, 150, 143, 24);
        add(tenderFac);
        tenderFac.setColumns(10);
        
        tenderPrice = new JTextField();
        tenderPrice.setBounds(143, 220, 86, 24);
        add(tenderPrice);
        tenderPrice.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("投标数量");
        lblNewLabel.setBounds(29, 296, 72, 18);
        add(lblNewLabel);
        
        tenderNumber = new JTextField();
        tenderNumber.setBounds(143, 293, 86, 24);
        add(tenderNumber);
        tenderNumber.setColumns(10);
        
        JButton button = new JButton("确认投标");
        button.addActionListener(e -> {
            String name=tenderName.getText();
            String number=tenderNumber.getText();
            String factory=tenderFac.getText();
            int price=Integer.parseInt(tenderPrice.getText());
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String time=formatter.format(date);
            Tender tender=new Tender(orderNumber,orderName,name,factory,time,price,number,"竞标发布");
            try {
                if(tenderController.add(tender)){
                    JOptionPane.showMessageDialog(null,"投标成功");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        button.setBounds(29, 352, 113, 27);
        add(button);
        

    }
}
