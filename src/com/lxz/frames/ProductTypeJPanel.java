package com.lxz.frames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.lxz.controllers.ProductController;
import com.lxz.entity.ProductType;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

/**
 * @program: CMAPP
 * @description 产品类型管理面板
 * @author: 李星泽
 * @create: 2020-07-17 16:47
 **/
public class ProductTypeJPanel extends JPanel {
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private ProductController productController = new ProductController();
    private JTextField productType;
    private JTable table;

    /**
     * 创建产品类型管理面板
     */
    public ProductTypeJPanel() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("产品类型");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
        lblNewLabel.setBounds(108, 115, 116, 31);
        add(lblNewLabel);

        productType = new JTextField();
        productType.setBounds(303, 115, 194, 31);
        add(productType);
        productType.setColumns(10);

        JButton btnNewButton = new JButton("新建");
        btnNewButton.addActionListener(e -> {
            //判断要添加的产品类型是否为空
            if (productType.getText() == null || productType.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入你要添加的设备类型");
                return;
            }
            //新建产品类型对象
            ProductType type = new ProductType();
            type.setTypeName(productType.getText());
            try {
                //添加产品对象
                productController.addType(type);
                defaultTableModel.setRowCount(0);
                showMessage();
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton.setBounds(679, 182, 113, 27);
        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("检索");
        btnNewButton_1.addActionListener(e -> {
            if (productType.getText() == null || productType.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入你要查询的产品类型");
            }
            try {
                //通过产品类型进行检索
                ProductType type = (ProductType) productController.searchProductType(productType.getText());
                if (type == null) {
                    JOptionPane.showMessageDialog(null, "不存在该产品类型");
                } else {
                    defaultTableModel.setRowCount(0);
                    addTableRow(type, 1);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_1.setBounds(679, 280, 113, 27);
        add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("修改");
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int find = JOptionPane.showConfirmDialog(null, "是否确定要修改该产品类型", "温馨提示", JOptionPane.YES_NO_OPTION);
                if (find == 0) {
                    int num = table.getSelectedRow();
                    if (num < 0 || num > table.getRowCount() - 1) {
                        JOptionPane.showMessageDialog(null, "请选择你要修改的产品类型");
                        return;
                    }
                    try {
                        //以前的产品类型信息和更改的产品类型
                        boolean success = productController.modifyType(defaultTableModel.getValueAt(num, 1).toString(), productType.getText());
                        if (success) {
                            defaultTableModel.setRowCount(0);
                            showMessage();
                        } else {
                            JOptionPane.showMessageDialog(null, "修改失败，可能不存在该产品类型");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btnNewButton_2.setBounds(679, 384, 113, 27);
        add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("删除");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int find = JOptionPane.showConfirmDialog(null, "是否确定要修改该产品类型", "温馨提示", JOptionPane.YES_NO_OPTION);
                if (find == 0) {
                    int num = table.getSelectedRow();
                    if (num < 0 || num > table.getRowCount() - 1) {
                        JOptionPane.showMessageDialog(null, "请选择你要修改的产品类型");
                        return;
                    }

                    try {
                        boolean success = productController.deleteType(defaultTableModel.getValueAt(num, 1).toString());
                        if (success) {
                            defaultTableModel.removeRow(num);
                        } else {
                            JOptionPane.showMessageDialog(null, "修改失败，可能不存在该产品类型");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnNewButton_3.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton_3.setBounds(679, 501, 113, 27);
        add(btnNewButton_3);

        table = new JTable(defaultTableModel);
        table.setBounds(77, 182, 535, 349);
        table.setRowHeight(25);
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

    /**
     * @description 展示管理员信息
     * @throws IOException:
     */
    public void showMessage() throws IOException {

        List<Object> objects = productController.getProductType();
        for (int i = 0; i < objects.size(); i++) {
            addTableRow((ProductType) objects.get(i), i + 1);
        }
    }

    /**
     * @description 添加一行表格信息
     * @param productType:
     * @param number:序号
     */
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
