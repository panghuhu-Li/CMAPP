package com.lxz.frames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.TenderController;
import com.lxz.entity.Order;
import com.lxz.entity.Tender;

public class TenderInfoJPanel extends JPanel {
    private JTable table;
    private final DefaultTableModel defaultTableModel = new DefaultTableModel();
    private final TenderController tenderController=new TenderController();

    /**
     * Create the panel.
     */
    public TenderInfoJPanel() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("投标信息查看");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 40));
        lblNewLabel.setBounds(136, 54, 537, 109);
        add(lblNewLabel);
        
        table = new JTable(defaultTableModel);
        table.setBounds(32, 181, 782, 332);
        table.setRowSelectionAllowed(true);

        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(32, 181, 782, 332);
        add(scollPane);
        
        defaultTableModel.addColumn("序号");
        defaultTableModel.addColumn("订单编号");
        defaultTableModel.addColumn("订单名称");
        defaultTableModel.addColumn("投标人");
        defaultTableModel.addColumn("投标人工厂");
        defaultTableModel.addColumn("投标时间");
        defaultTableModel.addColumn("单价");
        defaultTableModel.addColumn("承载数量");
        defaultTableModel.addColumn("竞标状态");
        
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

        List<Object> objects = tenderController.getList();
        for (int i = 0; i < objects.size(); i++) {
            addTableRow((Tender) objects.get(i), i + 1);
        }
    }


    /**
     * @param num:序号
     * @description 添加一行表格信息
     */
    public void addTableRow(Tender tender, int num) {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        rowData.add(num);
        rowData.add(tender.getOrderNumber());
        rowData.add(tender.getOrderName());
        rowData.add(tender.getTenderPeo());
        rowData.add(tender.getTenderFactor());
        rowData.add(tender.getTenderDate());
        rowData.add(tender.getPrice());
        rowData.add(tender.getTenderNumber());
        rowData.add(tender.getTenderState());
        // 添加一行
        defaultTableModel.addRow(rowData); // 添加一行
    }
}
