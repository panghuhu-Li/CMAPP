package com.lxz.frames;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TenderJFrame extends JFrame {

    private JPanel contentPane;
    
    //单例模式
    private static TenderJFrame instanceFrame = null;
    
    public static TenderJFrame creatInstance() {
        if (instanceFrame == null) {
            instanceFrame = new TenderJFrame();
            return instanceFrame;
        } else {
            return instanceFrame;
        }
    }

   
    /**
     * Create the frame.
     */
    public TenderJFrame() {
        setBounds(100, 100, 367, 432);
        setLocationRelativeTo(null);
        setResizable(true);
//        changeContenePane(new TenderJPanel());
    }
    
    public void changeContenePane(Container contentPane) {
        setContentPane(contentPane);
        // 重新启动
        this.revalidate();
    }
    
  // 改变面板
    public void changeJFrame(String titleString, JPanel jpanle) {
        AddOrderJFrame addOrderJFrame = AddOrderJFrame.creatInstance();
        addOrderJFrame.setVisible(true);
        setTitle(titleString);
        addOrderJFrame.setBounds(100, 100, 367, 432);
        addOrderJFrame.changeContenePane(jpanle);
        addOrderJFrame.setLocationRelativeTo(null);
        addOrderJFrame.setResizable(false);
    }

}
