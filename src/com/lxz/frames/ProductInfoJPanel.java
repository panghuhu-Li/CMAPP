package com.lxz.frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.ProductController;
import com.lxz.entity.ProductInfo;
import com.lxz.entity.ProductType;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

/**
 * @program: CMAPP
 * @description 产品信息管理面板
 * @author: 李星泽
 * @create: 2020-07-17 14:23
 **/
public class ProductInfoJPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField productName;
    private JTextField productSpec;
    private JTextField productDesc;
    private JTable table;
    private String choose = "";
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private ProductController productController = new ProductController();

    /**
     * 创建产品信息管理面板
     */
    public ProductInfoJPanel() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("产品名称");
        lblNewLabel.setBounds(59, 60, 101, 26);
        add(lblNewLabel);

        productName = new JTextField();
        productName.setBounds(211, 61, 139, 24);
        add(productName);
        productName.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("产品类别");
        lblNewLabel_1.setBounds(444, 64, 72, 18);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("产品规格");
        lblNewLabel_2.setBounds(59, 134, 72, 18);
        add(lblNewLabel_2);

        productSpec = new JTextField();
        productSpec.setBounds(211, 134, 139, 24);
        add(productSpec);
        productSpec.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("产品描述");
        lblNewLabel_3.setBounds(444, 134, 72, 18);
        add(lblNewLabel_3);

        productDesc = new JTextField();
        productDesc.setBounds(627, 131, 174, 24);
        add(productDesc);
        productDesc.setColumns(10);

        //动态添加产品类型
        List<Object> objects = null;
        try {
            objects = productController.getProductType();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JComboBox<String> comboBox = new JComboBox<String>();
        //若无任何产品类型显示无
        if (objects == null || objects.size() == 0) {
            comboBox.addItem("无任何产品类型");
        } else {
            for (int i = 0; i < objects.size(); i++) {
                ProductType productType = (ProductType) objects.get(i);
                comboBox.addItem(productType.getTypeName());
            }
        }
        comboBox.addActionListener(e -> choose = comboBox.getSelectedItem().toString());
        comboBox.setBounds(627, 61, 174, 24);
        add(comboBox);

        table = new JTable(defaultTableModel);
        table.setBounds(40, 236, 765, 288);
        table.setRowSelectionAllowed(true); // 整行选择

        JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(40, 236, 765, 288);
        add(scollPane);


        JButton btnNewButton = new JButton("新建");
        btnNewButton.addActionListener(e -> {
            ProductInfo productInfo = new ProductInfo();
            //随机产生产品编号
            productInfo.setProductNumber("PNO" + (100000 + Math.random() * 890000));
            //判断输入是否合乎规则
            if (productDesc.getText() == null || productDesc.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "产品描述不能为空");
                return;
            } else {
                productInfo.setProductDesc(productDesc.getText());
            }
            if (productName.getText() == null || productName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "产品名称不能为空");
                return;
            } else {
                productInfo.setProductName(productName.getText());
            }
            if (productSpec.getText() == null || productSpec.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "产品规格不能为空");
                return;
            } else {
                productInfo.setProductSpec(productSpec.getText());
            }
            if (choose == null || choose.equals("")) {
                JOptionPane.showMessageDialog(null, "请选择产品类型");
                return;
            } else {
                productInfo.setProductType(choose);
            }

            try {
                //产品信息添加
                boolean success = productController.add(productInfo);
                if (success) {
                    JOptionPane.showMessageDialog(null, "产品信息添加成功");
                    //清空操作
                    defaultTableModel.setRowCount(0);
                    showMessage();
                } else {
                    JOptionPane.showMessageDialog(null, "已存在该编号，请重新添加");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton.setBounds(47, 190, 113, 27);
        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("删除");
        btnNewButton_1.addActionListener(e -> {
            //判断是否删除，YES==0，NO==1
            int find = JOptionPane.showConfirmDialog(null, "是否确定要删除该产品信息", "温馨提示", JOptionPane.YES_NO_OPTION);
            if (find == 0) {
                //获得选中的行
                int num = table.getSelectedRow();
                //判断是否选中行
                if (num < 0 || num > table.getRowCount() - 1) {
                    JOptionPane.showMessageDialog(null, "请选择你要删除的产品");
                    return;
                }

                try {
                    boolean success = productController.delete(defaultTableModel.getValueAt(num, 1).toString());
                    if (success) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败，不存在该商品");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                defaultTableModel.removeRow(num);
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_1.setBounds(257, 190, 113, 27);
        add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("检索");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //通过产品名称进行检索
                    ProductInfo productInfo = (ProductInfo) productController.searchProductInfo(productName.getText());
                    if (productInfo == null) {
                        JOptionPane.showMessageDialog(null, "不存在该产品");
                        return;
                    } else {
                        defaultTableModel.setRowCount(0);
                        addTableRow(productInfo, 1);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_2.setBounds(473, 190, 113, 27);
        add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("修改");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //获得要修改的行
                int num = table.getSelectedRow();
                if (num < 0 || num > table.getRowCount() - 1) {
                    JOptionPane.showMessageDialog(null, "请选择你要修改的行");
                    return;
                }
                //获得修改后的姓名
                String prouctN;
                if (productName.getText() == null || productName.equals("")) {
                    prouctN = defaultTableModel.getValueAt(num, 2).toString();
                } else {
                    prouctN = productName.getText();
                }
                //获得产品类型
                if (choose == null || choose.equals("")) {
                    choose = defaultTableModel.getValueAt(num, 3).toString();
                }
                //获得产品规格
                String productS;
                if (productSpec.getText() == null || productSpec.getText().equals("")) {
                    productS = defaultTableModel.getValueAt(num, 4).toString();
                } else {
                    productS = productSpec.getText();
                }
                //获得产品描述
                String productD;
                if (productDesc.getText() == null || productDesc.getText().equals("")) {
                    productD = defaultTableModel.getValueAt(num, 5).toString();
                } else {
                    productD = productDesc.getText();
                }
                //修改产品信息
                try {
                    boolean success = productController.modify(defaultTableModel.getValueAt(num, 1).toString(), prouctN, choose, productS, productD);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "修改成功");
                        //清空操作
                        defaultTableModel.setRowCount(0);
                        //展示修改后的信息
                        showMessage();
                    } else {
                        JOptionPane.showMessageDialog(null, "修改错误，可能不存在该产品信息");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_3.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_3.setBounds(688, 190, 113, 27);
        add(btnNewButton_3);

        defaultTableModel.addColumn("序号");
        defaultTableModel.addColumn("产品编号");
        defaultTableModel.addColumn("产品名称");
        defaultTableModel.addColumn("产品类别");
        defaultTableModel.addColumn("产品规格");
        defaultTableModel.addColumn("产品描述");

        try {
            showMessage();
        } catch (IOException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }

    }

    /**
     * @throws IOException:
     * @description 展示产品信息
     */
    public void showMessage() throws IOException {

        List<Object> objects = productController.getProductInfo();
        for (int i = 0; i < objects.size(); i++) {
            addTableRow((ProductInfo) objects.get(i), i + 1);
        }
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
